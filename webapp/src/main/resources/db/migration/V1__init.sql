CREATE TABLE Users (
  id BIGINT(20) PRIMARY KEY,
  email VARCHAR(255),
  name VARCHAR(255),
  password VARCHAR(255),
  banned BIT(1),
  blocked BIT(1),
  user_role VARCHAR(255),
  enabled BIT(1),
  locked BIT(1)
);

INSERT INTO Users VALUES (33, 'patkoc11@interia.pl', 'Pako2425', '$2a$10$3/lvhHwYGHpE8FpDIKNgBefoXTAHoeJ8h0LlxFYH/P29iaUzvMv1S', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (34, 'a@g.com', 'a', '$2a$10$NHpePvCUbS/.pzG.TWSDjua5jNSjW2YIioyM77lpzxpiK9qcbB3ta', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (35, 'b@g.com', 'b', '$2a$10$zmlSYra1vZpfUjR8Ubkx.Op6bd0R8acNII4t3CfT2a19kcsrSje.S', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (36, 'c@g.com', 'c', '$2a$10$DYLHyLOkzQzJWa8yn.txyODRqqLV7RXxjcdAKPF1aE3LI.uf9bncO', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (37, 'Pako2425', 'a@g.com', '$2a$10$rH8SMbVjzp5MAiOJ6KWS..b5ggfSB.pa6OVsZM2OxYiVlJF.ij/eK', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (38, 'd@g.com', 'd', '$2a$10$ggK0w0N3166u0oiAknMQ1edS/y91ey4fDMMv0jBlPcTAf9ZNTpwDa', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (39, 'e@g.com', 'e', '$2a$10$.teSf1tUMmcH6eKNUdKVKekzGd.iFGaGi6cSRU/aFZNPnxPWAGvbK', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (40, 'h@g.com', 'f', '$2a$10$2W/bKutR/duQaKQJ85Xxw.JagbWAtQAzWE2y3G21hLeWX89RvQVfq', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (41, 'aa@g.com', 'aa', '$2a$10$wxht28GtR1ZhcDUHCW3qruNVq2vjIcXcpwNGVCqrbDpJaSsOg4ute', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (42, 'bb@g.com', 'bb', '$2a$10$VHyVwlXWiGdqRf4/vB33K.BU5ZRP/oenHGKKiqcIU6RJNt2UObh.e', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (43, 'cc@g.com', 'cc', '$2a$10$t870mfyc1Xzk1W.nA7NxU.LbGiJd0lEJUwOpluUDxXmsOVSNK8UgS', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (44, 'dd@g.com', 'dd', '$2a$10$60x8MCkIZ4eT/t51Ms2k5.uJ1mz12EDNl9NEXlsZN4lSa.fge60Xu', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (45, 'ee@g.com', 'ee', '$2a$10$eH0ozHJQ5yMJGsU4pMh.K.RBVFtV1hzOlxvIRmz3e2/zuCIOqqVr.', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (46, 'ff@g.com', 'ff', '$2a$10$tWVtQ/L85sDjTl.LFwYzTOBz4jbcdNq/ixna8sEEv7NH8SYPwqkZu', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (47, 'z@g.com', 'z', '$2a$10$Is8VzYKdbyd9.i.Lr1qKSehwnVl1HJzV1VGbG0yn31mRSrZCU69Da', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (48, 'zz@g.com', 'zz', '$2a$10$IJF0bKa9uScscqXBaL6I6eaWn4y/iceim9cvynBNurzQsx5.mUexG', NULL, NULL, 'ROLE_USER', 1, 0);
INSERT INTO Users VALUES (49, 'admin@g.com', 'admin', '$2a$10$tXMTbxksxuLmHNoGfrk0eepBfNcPiJUoyx/SX86JKcM8CSMSVO9Cy', NULL, NULL, 'ROLE_ADMIN', 1, 0);

CREATE TABLE Memes (
  id BIGINT(20) PRIMARY KEY,
  file_path VARCHAR(255),
  title VARCHAR(255),
  username VARCHAR(255),
  blocked BIT(1),
  likes_number INT(11),
  user_id BIGINT(20)
);

INSERT INTO Memes VALUES (16, 'D:/memes/abc.jpg', 'abc', NULL, 0, 0, 0);
INSERT INTO Memes VALUES (17, 'D:/memes/zzz.jpg', 'zzz', NULL, 0, 0, 0);
INSERT INTO Memes VALUES (18, 'D:/memes/rtuyinbv.jpg', 'rtuyinbv', NULL, 0, 0, 0);
INSERT INTO Memes VALUES (19, 'D:/memes/sfhjkuyyfgfg.jpg', 'sfhjkuyyfgfg', NULL, 0, 0, 0);
INSERT INTO Memes VALUES (20, 'D:/memes/lk,nmgngfhnhdfgh.jpg', 'lk,nmgngfhnhdfgh', NULL, 0, 0, 0);
INSERT INTO Memes VALUES (21, 'D:/memes/sdfgjm.jpg', 'sdfgjm', NULL, 0, 0, 0);
INSERT INTO Memes VALUES (23, 'D:/memes/Tytul.jpg', 'Tytul', NULL, 0, 0, 0);
INSERT INTO Memes VALUES (24, 'D:/memes/Podobna do ojca.jpg', 'Podobna do ojca', NULL, 0, 0, 0);
INSERT INTO Memes VALUES (25, 'D:/memes/Poczta.jpg', 'Poczta', NULL, 0, 0, 0);
INSERT INTO Memes VALUES (27, 'D:/memes/Porzadek.jpg', 'Porzadek', NULL, 0, 0, 0);
