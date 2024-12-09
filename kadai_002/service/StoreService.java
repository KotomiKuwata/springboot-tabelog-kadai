package com.example.kadai_002.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.kadai_002.entity.Store;
import com.example.kadai_002.form.StoreEditForm;
import com.example.kadai_002.form.StoreRegisterForm;
import com.example.kadai_002.repository.StoreRepository;

@Service

public class StoreService {
	private final StoreRepository storeRepository;

	public StoreService(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	@Transactional
	public void create(StoreRegisterForm storeRegisterForm) {
		Store store = new Store();
		MultipartFile imageFile = storeRegisterForm.getImageFile();

		if (!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
			try {
				Files.copy(imageFile.getInputStream(), filePath);
				store.setImageName(hashedImageName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		store.setName(storeRegisterForm.getName());
		store.setDescription(storeRegisterForm.getDescription());
		store.setOpeningHours(storeRegisterForm.getOpeningHours());
		store.setClosingTime(storeRegisterForm.getClosingTime());
		store.setPostalCode(storeRegisterForm.getPostalCode());
		store.setAddress(storeRegisterForm.getAddress());
		store.setPhoneNumber(storeRegisterForm.getPhoneNumber());
		store.setClosedDay(storeRegisterForm.getClosedDay());
		store.setCategoryId(storeRegisterForm.getCategoryId());

		storeRepository.save(store);
	}

	@Transactional
	public void update(StoreEditForm storeEditForm) {
		Store store = storeRepository.getReferenceById(storeEditForm.getId());
		MultipartFile imageFile = storeEditForm.getImageFile();

		if (imageFile != null && !imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
			try {
				Files.copy(imageFile.getInputStream(), filePath);
				store.setImageName(hashedImageName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		store.setName(storeEditForm.getName());
		store.setDescription(storeEditForm.getDescription());
		store.setOpeningHours(storeEditForm.getOpeningHours());
		store.setClosingTime(storeEditForm.getClosingTime());
		store.setPostalCode(storeEditForm.getPostalCode());
		store.setAddress(storeEditForm.getAddress());
		store.setPhoneNumber(storeEditForm.getPhoneNumber());
		store.setClosedDay(storeEditForm.getClosedDay());
		store.setCategoryId(storeEditForm.getCategoryId());

		storeRepository.save(store);
	}

	// UUIDを使って生成したファイル名を返す
	public String generateNewFileName(String fileName) {
		String[] fileNames = fileName.split("\\.");
		for (int i = 0; i < fileNames.length - 1; i++) {
			fileNames[i] = UUID.randomUUID().toString();
		}
		String hashedFileName = String.join(".", fileNames);
		return hashedFileName;
	}

	// 画像ファイルを指定したファイルにコピーする
	public void copyImageFile(MultipartFile imageFile, Path filePath) {
		try {
			Files.copy(imageFile.getInputStream(), filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Integer> getBusinessHours(Store store, LocalDate date) {
		if (store.getOpeningHours() == null || store.getClosingTime() == null) {
			return new ArrayList<>();
		}

		List<Integer> hours = new ArrayList<>();
		LocalTime openingTime = store.getOpeningHours();
		LocalTime closingTime = store.getClosingTime();

		for (int hour = openingTime.getHour(); hour <= closingTime.getHour() - 2; hour++) {
			hours.add(hour);
		}

		return hours;
	}

	public boolean isClosedDay(Store store, LocalDate date) {
		// 店舗の定休日を取得
		String closedDay = store.getClosedDay();

		// 定休日が設定されていない場合はfalse
		if (closedDay == null || closedDay.isEmpty()) {
			return false;
		}

		// 日付から曜日を取得（1:月曜日 ～ 7:日曜日）
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int dayValue = dayOfWeek.getValue();

		// カンマ区切りの定休日文字列を配列に分割
		String[] closedDays = closedDay.split(",");

		// 定休日かどうかをチェック
		for (String day : closedDays) {
			// 曜日名を数値に変換するロジックを追加
			int closedDayValue;
			switch (day.trim()) {
			case "月曜日":
				closedDayValue = 1;
				break;
			case "火曜日":
				closedDayValue = 2;
				break;
			case "水曜日":
				closedDayValue = 3;
				break;
			case "木曜日":
				closedDayValue = 4;
				break;
			case "金曜日":
				closedDayValue = 5;
				break;
			case "土曜日":
				closedDayValue = 6;
				break;
			case "日曜日":
				closedDayValue = 7;
				break;
			default:
				continue; // 不正な形式の場合はスキップ
			}
			if (closedDayValue == dayValue) {
				return true;
			}
		}
		return false;
	}

}
