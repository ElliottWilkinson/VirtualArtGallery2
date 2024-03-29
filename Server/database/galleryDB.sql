BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_info, art_info, gallery_info, gallery_item;

CREATE TABLE user_info (

	user_id serial PRIMARY KEY,
	user_name varchar(80) NOT NULL,
	password_hash varchar(80) NOT NULL,
	email_address varchar(80) NOT NULL,
	role varchar (50) NOT NULL

);

CREATE TABLE art_info (

	art_id serial PRIMARY KEY,
	image_name varchar(500) NOT NULL,
	caption varchar(80),
	description varchar(500),
	user_id integer,
	CONSTRAINT fk_artist_id FOREIGN KEY (user_id) REFERENCES user_info(user_id)

);

CREATE TABLE gallery_info (

    gallery_id serial PRIMARY KEY,
    gallery_name varchar(80),
	user_id integer,
	CONSTRAINT fk_gallery_user FOREIGN KEY (user_id) REFERENCES user_info(user_id)

);

CREATE TABLE gallery_item (

    gallery_item_id serial PRIMARY KEY,
    gallery_id int NOT NULL,
    art_id int NOT NULL,
    CONSTRAINT fk_gallery_item_gallery FOREIGN KEY (gallery_id) REFERENCES gallery_info(gallery_id),
    CONSTRAINT fk_gallery_item_art FOREIGN KEY (art_id) REFERENCES art_info(art_id)

    );

--INSERT STATEMENTS

--user information

INSERT INTO user_info (user_name, email_address, password_hash, role) VALUES
('admin', 'admin@admin.com', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_ADMIN'),
('battlebunneart', 'battlebunneart@user.com', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_USER'),
('silentchillz', 'silentchillz@user.com', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_USER'),
('remnantof', 'remnantof@user.com', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_USER'),
('bacoose', 'bacoose@user.com', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem', 'ROLE_USER'); --user ID 5

--art information

INSERT INTO art_info (image_name, caption, description, user_id) VALUES
('./img/battleBunneArtPiece1.png', 'Aphrodite', 'fanart for the roguelike Hades', 2), --art ID 1
('./img/battleBunneArtPiece2.png', 'Ceres', 'original design for a dnd player character', 2), --art ID 2
('./img/battleBunneArtPiece3.png', 'Leo', 'valentines variant of a dnd player character', 2), --art ID 3
('./img/battleBunneArtPiece4.png', 'Velvari', 'original design for a dnd player character', 2), --art ID 4
('./img/silentChillzPiece1.png', 'Jeza', 'design sheet for a dnd player character', 3), --art ID 5
('./img/silentChillzPiece2', 'Malyvaly', 'couple piece of 2 dnd player characters', 3), --art ID 6
('./img/silentChillzPiece3', 'Vin', 'original design for a dnd player character', 3), --art ID 7
('./img/silentChillzPiece4', 'Zaiwure', 'original design for baldurs gate 3 tav', 3), --art ID 8
('./img/remnantOfPiece1', 'Pixel Landscape 1', 'pixel design for landscape - part of pair', 4), --art ID 9
('./img/remnantOfPiece2', 'Pixel Landscape 2', 'pixel design for landscape - part of pair', 4), --art ID 10
('./img/bacoosePiece1', 'Mulligan', 'bust design for original character', 5), --art ID 11
('./img/bacoosePiece2', 'Pynk', 'original design for a dnd player character', 5), --art ID 12
('./img/bacoosePiece3', 'The Alchemist', 'couple art depicting a backstory moment for a dnd player character', 5), --art ID 13
('./img/bacoosePiece4', 'Gone', 'token art for a dnd player character', 5); --art ID 14

--gallery information

INSERT INTO gallery_info (user_id, gallery_name) VALUES
(2, 'BattleBunneArt'), --gallery 1
(3, 'SilentChillz'), --gallery 2
(4, 'RemnantOf'), --gallery 3
(5, 'Bacoose'); --gallery 4

--gallery items

INSERT INTO gallery_item (gallery_id, art_id) VALUES

(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(3, 9),
(3, 10),
(4, 11),
(4, 12),
(4, 13),
(4, 14);

COMMIT;