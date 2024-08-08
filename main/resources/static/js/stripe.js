document.addEventListener("DOMContentLoaded", function() {
    // Stripe.jsをインクルード
    var stripe = Stripe('pk_test_51PjGIyFRY5VG4shkpqJOgbBIKa42NRQ4ZHuP5C03X6btIJnLFaAunpdOBc9t9fc04txhW8BiGFvdjkQIxb15gk0D00fYp1C0Uy'); // ここに公開可能なStripeキーを入れる

    var elements = stripe.elements();
    var card = elements.create('card');
    card.mount('#card-element');

    card.on('change', function(event) {
        var displayError = document.getElementById('card-errors');
        if (event.error) {
            displayError.textContent = event.error.message;
        } else {
            displayError.textContent = '';
        }
    });

    // フォームのSubmitイベントをリッスン
    var form = document.getElementById('payment-form');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // 利用規約を確認
        var termsCheckbox = document.getElementById('termsCheckbox');
        if (!termsCheckbox.checked) {
            document.getElementById('error-message').style.display = 'block';
            return;
        } else {
            document.getElementById('error-message').style.display = 'none';
        }

        // Stripe.jsでカード情報からトークンを作成
        stripe.createToken(card).then(function(result) {
            if (result.error) {
                // エラーメッセージを表示
                var errorElement = document.getElementById('card-errors');
                errorElement.textContent = result.error.message;
            } else {
                // サーバーへトークンを送信
                stripeTokenHandler(result.token);
            }
        });
    });

    function stripeTokenHandler(token) {
        // フォームにStripeトークンを追加
        var form = document.getElementById('payment-form');
        var hiddenInput = document.createElement('input');
        hiddenInput.setAttribute('type', 'hidden');
        hiddenInput.setAttribute('name', 'stripeToken');
        hiddenInput.setAttribute('value', token.id);
        form.appendChild(hiddenInput);

        // フォームを送信
        form.submit();
    }
});