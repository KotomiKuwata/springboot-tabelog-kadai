--storesテーブル
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (1, '握り飯 わか', 'tenmusu.jpg', '天むすとお惣菜を盛り込んだ人気のお弁当も販売', '10:00', '19:00', '481-0000', '北名古屋市石橋XX-X', '0569-XX-XXXX', '月曜日', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (2, 'Nagoya kitchen', 'nagoya.jpg', 'エビフライ、名古屋コーチンカツ、味噌のどて煮、とんかつが一つの丼に!!', '11:00', '21:00', '481-0000', '北名古屋市石橋XX-X', '0569-XX-XXXX', '水曜日', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (3, '味噌カツ かつ亭', 'misokatsu.jpg', 'B級グルメ定番味噌カツ専門店', '11:00' ,'21:00' ,'456-0000', '名古屋市熱田区XX-XX', '052-XXX-XXXX', '年中無休', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (4, '喫茶 水曜館', 'morning.jpg','サイフォンで入れたコーヒーと小倉トーストのモーニングセットおすすめ ', '07:00', '20:00', '456-0000', '名古屋市熱田区XX-XX', '052-XXX-XXXX', '日曜日', 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (5, '寿司 二心', 'hitsumabushi.jpg', 'ランチひつまぶし2000円', '11:00', '22:00','456-0000', '名古屋市熱田区XX-XX', '052-XXX-XXXX', '日曜日', 17, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (6, '饂飩屋あおき','misonikomiudon.jpg' ,'定番うどんから味噌煮込みうどんも美味しいうどん屋', '11:00', '20:00',  '462-0000', '名古屋市北区X-XX', '052-XXX-XXXX', '年中無休', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (7, '食事処 和み','doteni.jpg' , 'どて煮定食が人気のお店' ,'11:00' , '21:00', '462-0000', '名古屋市北区X-XX', '052-XXX-XXXX', '日曜日', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (8, 'cafe ナーポ', 'teppanspa.jpg', 'ランチに人気鉄板ナポリタン', '07:00', '20:00', '466-0000', '名古屋市昭和区XX-XX',  '052-XXX-XXXX', '年中無休', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (9,'めん亭 ', 'kishimen.jpg', 'あかつゆのきしめんをメインメニューにカレー等バラエティー豊かなメニューも揃っています', '11:00', '20:00', '466-0000', '名古屋市昭和区XX-XX',  '052-XXX-XXXX', '月曜日', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (10, '呑み処 呑や', 'misokatsu.jpg','味噌カツ、どて煮、手羽先揚げ、お酒に合う名古屋グルメ揃ってます' , '17:00', '02:00', '466-0000', '名古屋市昭和区XX-XXX',  '052-XXX-XXXX', '日曜日', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (11, '居酒屋 さくら', 'tebasaki.jpg', '人気メニューは「幻の手羽先」', '17:00', '02:00', '464-0000', '名古屋市千種区XX-XX',  '052-XXX-XXXX', '年中無休', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (12,'鳥吉' , 'nagoyacochin.jpg',' 名古屋コーチンの肉と卵を合わせた親子丼のランチ', '17:00', '00:00', '464-0000', '名古屋市千種区XX-XX',  '052-XXX-XXXX', '日曜日', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (13, '山崎屋総本家', 'misonikomiudon.jpg', '豆味噌を用いたうまみのある味わいの味噌煮込みうどん', '11:00', '20:00', '468-0000', '名古屋市天白区',  '052-XXX-XXXX', '年中無休', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (14, '喫茶 風船', 'ankakespa.jpg', '野菜をたっぷり使ったヘルシーあんかけスパ', '07:00', '20:00', '468-0000', '名古屋市天白区XX-XX',  '052-XXX-XXXX', '年中無休', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (15, '桔梗', 'hitsumabushi.jpg', '情緒あふれる日本家屋で伝統のひつまぶしを味わえます', '11:00', '22:00', '468-0000', '名古屋市天白区XX-XXX',  '052-XXX-XXXX', '年中無休', 17, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (16, '風見鶏', 'tebasaki.jpg', 'パンチのあるしっかりした味付けの手羽先唐揚げ','17:00', '02:00', '454-0000', '名古屋市中川区XX-XX',  '052-XXX-XXXX',  '年中無休', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (17, 'とり良', 'nagoyacochin.jpg', '新鮮でこだわり抜かれた豊富なコーチン料理を堪能でき', '17:00', '00:00', '454-0000', '名古屋市中川区XXX-XX',  '052-XXX-XXXX', '年中無休', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (18, 'カツ将', 'misokatsu.jpg', '1年半じっくりと熟成させた豆味噌を使用したみそだれがサクサク衣にマッチ', '11:00', '22:00', '460-0000', '名古屋市中区XX-XX',  '052-XXX-XXXX',  '年中無休', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (19, 'あいさわ食堂','doteni.jpg' ,'老舗でいただく継ぎ足しのどて煮は絶品' , '11:00', '21:00', '460-0000', '名古屋市中区XX-XX',  '052-XXX-XXXX', '年中無休', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (20,'京月', 'uiro.jpg', '切り分けなくても食べられる一口ういろ', '10:00', '18:00', '460-0000', '名古屋市中区XXX-XX',  '052-XXX-XXXX', '水曜日', 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (21, 'もちづき', 'kishimen.jpg', '出汁にこだわり抜いた「きしめん」を楽しめる', '11:00', '21:00', '453-0000', '名古屋市中村区XX-XX', '052-XXX-XXXX', '年中無休', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (22, 'ふぇにっくす', 'nagoyacochin.jpg' ,'純系名古屋コーチンとコーチン料理にぴったりなお酒を堪能できるお店' , '17:00', '23:00', '453-0000', '名古屋市中村区XXX-XX',  '052-XXX-XXXX', '年中無休', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (23,'香霖堂' , 'onimanju.jpg', '角切りにしたさつま芋を混ぜて蒸した鬼まんじゅう', '07:00', '20:00', '453-0000', '名古屋市中村区XX-XXX',  '052-XXX-XXXX', '水曜日', 14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (24,'朝日屋' , 'humanju.jpg', 'モチモチとした食感の生麩に、上品な味わいのこしあんが包み込まれています。', '07:00', '20:00', '451-0000', '名古屋市西区XX-XX',  '052-XXX-XXXX', '年中無休', 13, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (25,'庭カフェ' , 'oguratoast.jpg' , '自家製コンフィチュールが乗った絶品小倉トースト', '07:00', '20:00', '451-0000', '名古屋市西区XX-XXXX',  '052-XXX-XXXX', '水曜日', 25, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (26, '福丸',  'kishimen.jpg','もちもちしていながらもしっかりとコシのあるきしめんが楽しめます' ,'07:00', '20:00', '461-0000', '名古屋市東区XX-XXX',  '052-XXX-XXXX', '年中無休', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (27,'台湾ラーメン 鳳仙' , 'taiwanramen.jpg', '台湾とつくものの名古屋がオリジナルのご当地グルメ', '11:00', '21:00', '461-0000', '名古屋市東区XXX-XX',  '052-XXX-XXXX', '年中無休', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (28, '喫茶 ルンバ', 'teppanspa.jpg','鉄板にひかれたアツアツの半熟卵は、おいておく時間によってお好みの固さでいただけます。' , '7:00', '20:00', '467-0000', '名古屋市瑞穂区XXX-XX',  '052-XXX-XXXX', '月曜日', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (29,'東' , 'nagoyacochin.jpg', '最高金賞受賞の名古屋コーチン親子丼をご堪能ください。', '11:00', '22:00', '467-0000', '名古屋市瑞穂区XX-XX',  '052-XXX-XXXX', '月曜日', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (30, '居酒屋呑ん太', 'tebasaki.jpg', '皮はパリッ、身はふっくらと最高の焼き上がりでご提供', '17:00', '02:00', '458-0000', '名古屋市緑区XX-XXX',  '052-XXX-XXXX', '日曜日', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (31, 'かしわ', 'nagoyacochin.jpg', '純系名古屋コーチンを刺身や炙りなど豊富な調理で堪能できます', '17:00', '00:00', '458-0000', '名古屋市緑区X-XX',  '052-XXX-XXXX', '月曜日', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (32,'芋しん' , 'imokenpi.jpg', '自家製蜜で表面をコーティングした細切りタイプの芋けんぴ', '10:00', '19:00', '455-0000', '名古屋市港区XX-X',  '052-XXX-XXXX', '年中無休', 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (33, 'とんかつ あじでん', 'misokatsu.jpg', '大判でボリューミーなロースとんかつをみそとソースの2種類の味で楽しめます。', '11:00', '22:00', '455-0000', '名古屋市港区XX-XX',  '052-XXX-XXXX',  '年中無休', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (34, 'でんや', 'misooden.jpg', '八丁味噌などの赤味噌をつかった甘辛仕上げの味噌おでん、迷ったら盛り合わせがおすすめ。', '17:00', '00:00', '457-0000', '名古屋市南区XXX-XX',  '052-XXX-XXXX',  '年中無休', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (35, 'raパスタ', 'ankakespa.jpg', '胡椒の効いたとろみのあるトマトソースに、炒めた極太麺をからめたボリューム満点のスパゲッティ', '11:00', '21:00', '457-0000', '名古屋市南区XXX-X',  '052-XXX-XXXX', '年中無休', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (36,'トリすけ' , 'tebasaki.jpg','甘辛タレがクセになる本場の「手羽先」でビールをグビリ !', '17:00', '22:00', '465-0000', '名古屋市名東区X-XXX',  '052-XXX-XXXX', '月曜日', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (37, '味噌煮込み ナワ','misonikomiudon.jpg' ,'自慢の味噌にこみうどんは、注文を受けてから手打ち麺を切り、煮込むというこだわり! ', '12:00', '21:00', '465-0000', '名古屋市名東区XX-XX',  '052-XXX-XXXX', '月曜日', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (38,'居酒屋 茶々', 'misooden.jpg','秘伝の味噌タレがじっくり染みた熱々の「みそおでん」や、どこまでもサックリ食感にこだわった名物の「串かつ」', '17:00', '01:00', '465-0000', '名古屋市名東区XX-XXX', '052-XXX-XXXX', '日曜日', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (39,'乙女屋' , 'teppanspa.jpg', '純系名古屋コーチンの濃厚でコクの深い卵を使用', ' 10:00', '19:00', '457-0000', '名古屋市守山区XXX-XX',  '052-XXX-XXXX', '年中無休', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO stores (id, name, image_name, description, opening_hours, closing_time, postal_code, address, phone_number, closed_day, category_id, created_at, updated_at) VALUES (40, '美良屋', 'tebasaki.jpg','古屋名物 名古屋コーチンの手羽先・鍋・串焼などお楽しみ頂けます。 ', '17:00', '02:00', '457-0000', '名古屋市守山区XX-X',  '052-XXX-XXXX', '月曜日', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--categoryテーブル
INSERT IGNORE INTO category (id, name) VALUES (1, 'きしめん');
INSERT IGNORE INTO category (id, name) VALUES (2, '味噌煮込みうどん');
INSERT IGNORE INTO category (id, name) VALUES (3, '台湾ラーメン');
INSERT IGNORE INTO category (id, name) VALUES (4, '味噌カツ');
INSERT IGNORE INTO category (id, name) VALUES (5, 'どて煮');
INSERT IGNORE INTO category (id, name) VALUES (6, '手羽先');
INSERT IGNORE INTO category (id, name) VALUES (7, '味噌おでん');
INSERT IGNORE INTO category (id, name) VALUES (8, '名古屋コーチン');
INSERT IGNORE INTO category (id, name) VALUES (9, 'あんかけスパ');
INSERT IGNORE INTO category (id, name) VALUES (10, '鉄板スパ');
INSERT IGNORE INTO category (id, name) VALUES (11, '芋けんぴ');
INSERT IGNORE INTO category (id, name) VALUES (12, 'ういろ');
INSERT IGNORE INTO category (id, name) VALUES (13, '生麩饅頭');
INSERT IGNORE INTO category (id, name) VALUES (14, '鬼まんじゅう');
INSERT IGNORE INTO category (id, name) VALUES (15, '天むす');
INSERT IGNORE INTO category (id, name) VALUES (16, 'トースト');
INSERT IGNORE INTO category (id, name) VALUES (17, 'ひつまぶし');

-- usersテーブル
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (1, '侍 太郎', 'サムライ タロウ', '2004-12-28', '090-1234-5678', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (2, '侍 花子', 'サムライ ハナコ', '1964-12-08', '090-1234-5678', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (3, '侍 義勝', 'サムライ ヨシカツ', '1947-04-15', '090-1234-5678', 'yoshikatsu.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (4, '侍 幸美', 'サムライ サチミ', '1977-11-19', '090-1234-5678', 'sachimi.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (5, '侍 雅', 'サムライ ミヤビ', '1940-04-12', '090-1234-5678', 'miyabi.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (6, '侍 正保', 'サムライ マサヤス', '1962-11-20', '090-1234-5678', 'masayasu.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (7, '侍 真由美', 'サムライ マユミ', '1985-10-03', '090-1234-5678', 'mayumi.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (8, '侍 安民', 'サムライ ヤスタミ', '1998-02-07', '090-1234-5678', 'yasutami.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (9, '侍 章緒', 'サムライ アキオ', '2006-01-16', '090-1234-5678', 'akio.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (10, '侍 祐子', 'サムライ ユウコ', '1988-08-02', '090-1234-5678', 'yuko.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (11, '侍 秋美', 'サムライ アキミ', '1982-10-08', '090-1234-5678', 'akimi.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO users (id, name, furigana, date_of_birth, phone_number, email, password, role_id, enabled, created_at, updated_at) VALUES (12, '侍 信平', 'サムライ シンペイ', '1961-05-14', '090-1234-5678', 'shinpei.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--reservationsテーブル
INSERT IGNORE INTO reservations (store_id, user_id, reservation_date, reservation_time, number_of_people, created_at, updated_at) VALUES (1, 1, '2024-07-25', '18:30:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO reservations (store_id, user_id, reservation_date, reservation_time, number_of_people, created_at, updated_at) VALUES (2, 2, '2024-07-26', '12:00:00', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO reservations (store_id, user_id, reservation_date, reservation_time, number_of_people, created_at, updated_at) VALUES (3, 3, '2024-07-27', '19:00:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO reservations (store_id, user_id, reservation_date, reservation_time, number_of_people, created_at, updated_at) VALUES (4, 4, '2024-07-28', '13:00:00', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT IGNORE INTO reservations (store_id, user_id, reservation_date, reservation_time, number_of_people, created_at, updated_at) VALUES (5, 5, '2024-07-29', '14:30:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--reviewsテーブル

--rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES 
(1, 'ROLE_GENERAL'), 
(2, 'ROLE_ADMIN'), 
(3, 'ROLE_PAID_MEMBER')

