BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO forum (name, description, rules)
    VALUES ('Techno', 'Join our discussion on today''s hottest techno DJ''s and their best tracks', 'No hateful language or illegal sharing of music'),
            ('HBO Hits', 'What''s your current favorite HBO show? Share with us, we need recs!', 'No hateful language or sharing of illegal videos'),
            ('Survivor', 'Are you a survivor superfan? We want to hear about your favorite seasons, players, and cutthroat tribal councils', 'No hateful language or sharing of illegal video'),
            ('NFL', 'Who is your favorite team? Who are you betting on this year?', 'No hateful language and NO EAGLES FANS. jk....'),
            ('Cat Club', 'Share your kitty pics! We need them.', 'No inappropriate media or hate speech');


INSERT INTO post (forum_id, user_id, title, body)
	VALUES (1, 1, 'Test title', 'Test body'), (2, 1, 'Test title2', 'Test body2'), (3, 1, 'Test title3', 'Test body3'), (4, 1, 'Test title4', 'Test body4'),(5, 1, 'Test title5', 'Test body5');
	

INSERT INTO moderator VALUES 
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5);

COMMIT TRANSACTION;
