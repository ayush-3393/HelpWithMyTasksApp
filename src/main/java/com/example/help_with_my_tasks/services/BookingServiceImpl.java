package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.constants.KafkaConstants;
import com.example.help_with_my_tasks.models.*;
import com.example.help_with_my_tasks.models.enums.BookingStatus;
import com.example.help_with_my_tasks.models.enums.HelperStatus;
import com.example.help_with_my_tasks.models.enums.TaskStatus;
import com.example.help_with_my_tasks.repositories.BookingRepository;
import com.example.help_with_my_tasks.repositories.HelpSeekerRepository;
import com.example.help_with_my_tasks.repositories.HelperRepository;
import com.example.help_with_my_tasks.services.service_interfaces.BookingService;
import com.example.help_with_my_tasks.services.service_interfaces.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PaymentService paymentService;
    private final HelpSeekerRepository helpSeekerRepository;
    private final HelperRepository helperRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);
    private final KafkaTemplate<String, Notification> kafkaTemplate;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              PaymentService paymentService,
                              HelpSeekerRepository helpSeekerRepository,
                              HelperRepository helperRepository,
                              KafkaTemplate<String, Notification> kafkaTemplate) {
        this.bookingRepository = bookingRepository;
        this.paymentService = paymentService;
        this.helpSeekerRepository = helpSeekerRepository;
        this.helperRepository = helperRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Optional<Booking> createBooking(Booking booking, Helper helper, Task task) {
        if (booking == null){
            return Optional.empty();
        }
        booking.setAmount(task.getTaskBudget());
        booking.setBookingStatus(BookingStatus.ACCEPTED);
        helper.setHelperStatus(HelperStatus.UNAVAILABLE);
        task.setTaskStatus(TaskStatus.BOOKED);
        booking.setHelper(helper);
        booking.setTask(task);
        booking.setCreatedAt(new Date());
        booking.setBookingDate(new Date());
        return Optional.of(bookingRepository.save(booking));
    }

    @Override
    public Optional<Booking> endBooking(Long bookingId, Rating rating) {
        if (bookingId == null){
            return Optional.empty();
        }
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isEmpty()){
            return Optional.empty();
        }
        Booking booking = bookingOptional.get();
        booking.setBookingStatus(BookingStatus.COMPLETED);
        booking.setDeleted(true);

        booking.setRatingFromHelperToHelpSeeker(rating.getRatingFromHelperToHelpSeeker());
        booking.setRatingFromHelpSeekerToHelper(rating.getRatingFromHelpSeekerToHelper());

        HelpSeeker helpSeeker = booking.getTask().getHelpSeeker();
        Helper helper = booking.getHelper();

        if (helpSeeker.getCountOfTasksCompleted() == null) {
            helpSeeker.setCountOfTasksCompleted(0);
        }
        if (helper.getCountOfBookingsCompleted() == null) {
            helper.setCountOfBookingsCompleted(0);
        }

        if (helpSeeker.getRating() == null){
            helpSeeker.setRating(0D);
        }
        if (helper.getRating() == null){
            helper.setRating(0D);
        }

        helpSeeker.updateRating(rating.getRatingFromHelperToHelpSeeker());
        helper.updateRating(rating.getRatingFromHelpSeekerToHelper());

        helpSeeker.setCountOfTasksCompleted(helpSeeker.getCountOfTasksCompleted() + 1);
        helper.setCountOfBookingsCompleted(helper.getCountOfBookingsCompleted() + 1);

        helpSeekerRepository.save(helpSeeker);
        helperRepository.save(helper);

        paymentService.createPayment(booking);
        return Optional.of(bookingRepository.save(booking));
    }

    @Override
    public Optional<List<Booking>> getAllBookingsForAHelper(Helper helper) {
        if (helper == null){
            return Optional.empty();
        }
        return bookingRepository.findAllByHelperId(helper.getId());
    }

    @Override
    public void sendNotification(Notification notification) {
        LOGGER.info("Sending notification: {}", notification.getNotificationMessage());
        Message<Notification> message =
                MessageBuilder
                        .withPayload(notification)
                        .setHeader(KafkaHeaders.TOPIC, KafkaConstants.BOOKING_TOPIC_NAME)
                        .build();
        kafkaTemplate.send(message);
    }

}
