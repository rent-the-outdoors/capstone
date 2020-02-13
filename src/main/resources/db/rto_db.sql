-- USE rto_db;
-- INSERT INTO activities(activity) VALUES ('hunting'), ('fishing'), ('boating'), ('camping');


USE rto_db;
TRUNCATE images;
INSERT INTO images(image_path, place_id) VALUES ('/img/hunt_icon.png', 1),
                                                ('/img/adventure-bg.jpg', 1),
                                                ('/img/adventure-bg.jpg', 1),
                                                ('/img/adventure-bg.jpg', 1),
                                                ('/img/adventure-bg.jpg', 1),
                                                ('/img/adventure-bg.jpg', 1);






INSERT INTO places(address, cost_per_day, description, title)
VALUES
('9898 Colonnade San Antonio, TX', '500.00', 'my apartment is for rent!', 'Nico\'s apartment'),
('Pearl Pkwy, San Antonio, TX 78215', '5000.00', 'the pearl is for rent', 'The Pearl'),
('Seguin, TX', '5000.00', 'my city of seguin is for rent!', 'Seguin');

INSERT INTO users(email, first_name, last_name, image_path, username, password, phone_num)
VALUES
('banana@gmail.com', 'Ba', 'Nana', null, 'banana', 'password', '210-273-5613');


