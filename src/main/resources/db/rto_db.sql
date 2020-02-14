-- USE rto_db;
-- INSERT INTO activities(activity) VALUES ('hunting'), ('fishing'), ('boating'), ('camping');

DROP DATABASE rto_db;
USE rto_db;
TRUNCATE reviews;
TRUNCATE users;
TRUNCATE images;
INSERT INTO images(image_path, place_id) VALUES ('/img/adventure-calm-clouds-dawn-414171.jpg', 1),
                                                ('/img/flock-of-birds-917494.jpg', 1),
                                                ('/img/green-trees-near-calm-body-of-water-2275221.jpg', 1),
                                                ('/img/camping.jpg', 1),
                                                ('/img/rockclimbing.jpeg', 1),
                                                ('/img/two-men-fishing-on-lake-1630039.jpg', 1);




INSERT INTO users(email, first_name, last_name, image_path, username, password, phone_num)
VALUES
('banana@gmail.com', 'Ba', 'Nana', null, 'banana', 'password', '210-273-5613');


INSERT INTO places(address, cost_per_day, description, title)
VALUES
('9898 Colonnade San Antonio, TX', '500.00', 'my apartment is for rent!', 'Nico\'s apartment'),
('Pearl Pkwy, San Antonio, TX 78215', '5000.00', 'the pearl is for rent', 'The Pearl'),
('Seguin, TX', '5000.00', 'my city of seguin is for rent!', 'Seguin');



