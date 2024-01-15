CREATE TABLE users (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255),
  name VARCHAR(255),
  password VARCHAR(255),
  banned BIT(1),
  blocked BIT(1),
  user_role VARCHAR(255),
  enabled BIT(1),
  locked BIT(1)
);

INSERT INTO users VALUES (33, 'patkoc11@interia.pl', 'Pako2425', '$2a$10$3/lvhHwYGHpE8FpDIKNgBefoXTAHoeJ8h0LlxFYH/P29iaUzvMv1S', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO users VALUES (49, 'admin@g.com', 'admin', '$2a$10$tXMTbxksxuLmHNoGfrk0eepBfNcPiJUoyx/SX86JKcM8CSMSVO9Cy', NULL, NULL, 'ROLE_ADMIN', 1, 0);

CREATE TABLE memes (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  file_path VARCHAR(255),
  title VARCHAR(255),
  username VARCHAR(255) DEFAULT NULL,
  blocked BIT(1) DEFAULT 0,
  likes_number INT(11) DEFAULT 0,
  user_id BIGINT(20) DEFAULT 0
);

INSERT INTO memes VALUES (1, 'https://www.dropbox.com/scl/fi/mbyx9jx1kae5zmjer47vu/ofensywa.jpg?rlkey=4nkupm3x74fy4o6zpana69njo&dl=0&dl=1', 'ofensywa', NULL, 0, 0, 0);
INSERT INTO memes VALUES (2, 'https://www.dropbox.com/scl/fi/3dztxzjgo4k6qs2t2dwr4/poczta.jpg?rlkey=rxw15xv63iproqtfbz8w9nda5&dl=0&dl=1', 'poczta', NULL, 0, 0, 0);
INSERT INTO memes VALUES (3, 'https://www.dropbox.com/scl/fi/ztxifzkk7ykprf76ao0w9/podobna-do-ojca.jpg?rlkey=2qhuh8u1z0c0g8bihh2mwmm36&dl=0&dl=1', 'podobna do ojca', NULL, 0, 0, 0);
INSERT INTO memes VALUES (4, 'https://www.dropbox.com/scl/fi/pc1vqhh9j7b5qf31xwbax/Porz-dek-kubek-z-kaw.jpg?rlkey=nx6u4taiab96h2q1yywmtp2em&dl=0&dl=1', 'Porządek, kubek z kawą', NULL, 0, 0, 0);
INSERT INTO memes VALUES (5, 'https://www.dropbox.com/scl/fi/790wu07jh2b6vebqqbw4r/Dzwon-na-skrzy-owaniu.jpg?rlkey=0qi7m1jkainmwds1iufd757h8&dl=0&dl=1', 'Dzwon na skrzyżowaniu', NULL, 0, 0, 0);
INSERT INTO memes VALUES (6, 'https://www.dropbox.com/scl/fi/ptquz79327xsw0w4h9mew/Wyj-tkowa-noc.jpg?rlkey=v62846a9f6574e9dmfs61ern7&dl=0&dl=1', 'Wyjątkowa noc', NULL, 0, 0, 0);
INSERT INTO memes VALUES (7, 'https://www.dropbox.com/scl/fi/jxms97dowmoar2gke4t1y/Silniki-jdm.jpg?rlkey=8ax63wn77tjza9s2pna120a3w&dl=0&dl=1', 'Silniki jdm', NULL, 0, 0, 0);
INSERT INTO memes VALUES (8, 'https://www.dropbox.com/scl/fi/w5735xmlph03d1idxxqmk/Supra-na-loterii.jpg?rlkey=h1hymrp4o06hfr91208hsarm4&dl=0&dl=1', 'Supra na loterii', NULL, 0, 0, 0);
INSERT INTO memes VALUES (9, 'https://www.dropbox.com/scl/fi/0i9bzbz6kvwc5sgel7n35/Komplementy.jpg?rlkey=vzynj7tivvz4vjya2ay5i6wtg&dl=0&dl=1', 'Komplementy', NULL, 0, 0, 0);
INSERT INTO memes VALUES (10, 'https://www.dropbox.com/scl/fi/4vmh7qe9bjyv9pykq5e5v/S-oiki.jpg?rlkey=symtxe81qnnaj317fokrs62m6&dl=0&dl=1', 'Słoiki', NULL, 0, 0, 0);
