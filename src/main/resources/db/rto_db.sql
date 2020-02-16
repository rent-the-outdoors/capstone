-- USE rto_db;
-- INSERT INTO activities(activity) VALUES ('hunting'), ('fishing'), ('boating'), ('camping');
CREATE DATABASE rto_db;
/*DROP DATABASE rto_db;
*/
USE rto_db;
DROP TABLE reviews;
DROP TABLE places;
DROP TABLE users;
DROP TABLE images;
TRUNCATE reviews;
TRUNCATE users;
TRUNCATE places;
TRUNCATE images;


INSERT INTO users(email, first_name, last_name, image_path, username, password, phone_num)
VALUES
('banana@gmail.com', 'Ba', 'Nana', '/img/male-avatar', 'banana', 'password', '210-273-5613');

INSERT INTO places(address, cost_per_day, description, title, user_id)
VALUES
('9898 Colonnade San Antonio, TX', '500.00', 'my apartment is for rent!', 'Nico\'s apartment', 1),
('Pearl Pkwy, San Antonio, TX 78215', '5000.00', 'the pearl is for rent', 'The Pearl', 1),
('Seguin, TX', '5000.00', 'my city of seguin is for rent!', 'Seguin', 1);


INSERT INTO images(image_path, place_id) VALUES ('/img/adventure-calm-clouds-dawn-414171.jpg', 1),
                                                ('/img/flock-of-birds-917494.jpg', 1),
                                                ('/img/green-trees-near-calm-body-of-water-2275221.jpg', 1),
                                                ('/img/camping.jpg', 1),
                                                ('/img/rockclimbing.jpeg', 1),
                                                ('/img/two-men-fishing-on-lake-1630039.jpg', 2),
                                                ('/img/adventure-calm-clouds-dawn-414171.jpg', 2),
                                                ('/img/flock-of-birds-917494.jpg', 2),
                                                ('/img/green-trees-near-calm-body-of-water-2275221.jpg', 2),
                                                ('/img/camping.jpg', 2),
                                                ('/img/rockclimbing.jpeg', 2),
                                                ('/img/two-men-fishing-on-lake-1630039.jpg', 2),
                                                ('/img/adventure-calm-clouds-dawn-414171.jpg', 3),
                                                ('/img/flock-of-birds-917494.jpg', 3),
                                                ('/img/green-trees-near-calm-body-of-water-2275221.jpg', 3),
                                                ('/img/camping.jpg', 3),
                                                ('/img/rockclimbing.jpeg', 3),
                                                ('/img/two-men-fishing-on-lake-1630039.jpg', 3);






