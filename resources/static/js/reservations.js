document.addEventListener('DOMContentLoaded', function() {
	const dateInput = document.getElementById('reservationDate');
	const hourSelect = document.getElementById('reservationHour');
	const form = document.querySelector('form[data-store-id]');

	if (!form) {
		console.error('予約フォームが見つかりません');
		return;
	}

	const store = form.dataset.storeId;

	if (!store) {
		console.error('店舗IDが見つかりません');
		return;
	}

	// 現在の日付を取得
	const today = new Date();
	dateInput.min = today.toISOString().split('T')[0];

	dateInput.addEventListener('change', function() {
		const selectedDate = new Date(this.value);

		// 選択された日付が今日の場合、現在時刻より前の時間を無効化
		fetch(`/api/stores/${store}/available-hours?date=${this.value}`)
			.then(response => {
				if (!response.ok) {
					throw new Error(`HTTP error! status: ${response.status}`);
				}
				return response.json();
			})
			.then(data => {
				hourSelect.innerHTML = '<option value="">選択してください</option>';

				if (data.isClosed) {
					dateInput.value = '';
					alert('選択された日付は定休日です。');
					return;
				}

				if (data.availableHours && Array.isArray(data.availableHours)) {
					data.availableHours.forEach(hour => {
						if (selectedDate.toDateString() === today.toDateString()) {
							const currentHour = today.getHours();
							if (hour > currentHour) {
								addTimeOption(hour);
							}
						} else {
							addTimeOption(hour);
						}
					});
				}
			})
			.catch(error => {
				console.error('Error:', error);
				hourSelect.innerHTML = '<option value="">エラーが発生しました</option>';
			});
	});

	function addTimeOption(hour) {
		const option = document.createElement('option');
		option.value = hour;
		option.textContent = `${hour}:00`;
		hourSelect.appendChild(option);
	}
});