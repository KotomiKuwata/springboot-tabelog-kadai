package com.example.kadai_002.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kadai_002.entity.Reservation;
import com.example.kadai_002.entity.Store;
import com.example.kadai_002.entity.User;
import com.example.kadai_002.form.ReservationRegisterForm;
import com.example.kadai_002.repository.ReservationRepository;
import com.example.kadai_002.repository.StoreRepository;
import com.example.kadai_002.repository.UserRepository;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final StoreRepository storeRepository;
	private final UserRepository userRepository;

	public ReservationService(ReservationRepository reservationRepository, StoreRepository storeRepository,
			UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.storeRepository = storeRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void create(ReservationRegisterForm reservationRegisterForm) {
		try {
			Reservation reservation = new Reservation();
			Store store = storeRepository.getReferenceById(reservationRegisterForm.getStoreId());
			User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
			LocalDateTime reservationDatetime = reservationRegisterForm.getReservationDatetime();

			reservation.setStore(store);
			reservation.setUser(user);
			reservation.setReservationDatetime(reservationDatetime);
			reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());

			reservationRepository.save(reservation);
		} catch (Exception e) {
			throw e; // トランザクションロールバック
		}
	}

	public boolean isValidReservationTime(Store store, LocalDateTime reservationDateTime) {

		LocalTime openingHours = store.getOpeningHours();
		LocalTime closingTime = store.getClosingTime();
		LocalTime reservationTime = reservationDateTime.toLocalTime();
		LocalTime lastReservationTime = closingTime.minusHours(2);

		boolean isValidStartTime =
				openingHours.equals(reservationTime) || openingHours.isBefore(reservationTime);

		boolean isBeforeOrAtLastReservation =
				lastReservationTime.isAfter(reservationTime) || reservationTime.equals(lastReservationTime);

		return isValidStartTime && isBeforeOrAtLastReservation;
	}
}