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
	VALUES (1, 1, '"Dad, can I borrow $10 worth of bitcoin??"', '"Borrow $11.62?...What the hell do you need $7.45 of bitcoin for?"'), (2, 1, 'I hate it when...', 'I hate it when job interviewers ask "what is your greatest strength," so I printed up these business cards to just hand out when asked.'), (3, 1, 'Place has ended.', 'Thank you to everyone who participated.

Maybe the real art was the friends we made along the way.'), (4, 1, 'Vladimir Putin`s black belt revoked by international taekwondo organization', 'I can’t imagine a more humiliating punishment than being demoted to being outranked by Steven Seagal.'),(5, 1, 'NEXT LEVEL', 'Woman takes the brunt of the impact of this car to save her child. Then another vehicle prevents the driver from escaping.');
	
	
INSERT INTO moderator VALUES 
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5);

COMMIT TRANSACTION;
