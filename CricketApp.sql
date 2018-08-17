create table team (team_id int auto_increment, team_name varchar(20), created_at datetime default current_timestamp, updated_at datetime default current_timestamp on update current_timestamp, constraint primary key (team_id));

create table player (team_id int, player_id int auto_increment, firstName varchar(20) unique, lastName varchar(20), line_up int, constraint primary key (player_id), constraint foreign key (team_id) references team (team_id), created_at datetime default current_timestamp, updated_at datetime default current_timestamp on update current_timestamp);

create table matches (match_id int auto_increment, match_date date, venue varchar(20), team_1_id int, team_2_id int, status varchar(10), toss_won_by int, match_won_by int, created_at datetime default current_timestamp, updated_at datetime default current_timestamp on update current_timestamp, constraint primary key (match_id), constraint foreign key (toss_won_by) references team (team_id), constraint foreign key (match_won_by) references team (team_id));

create table batting_info (match_id int, team_id int, player_id int, scored_run int, wicket_taker int, created_at datetime default current_timestamp, updated_at datetime default current_timestamp on update current_timestamp, constraint foreign key (match_id) references matches (match_id), constraint foreign key (team_id) references team (team_id), constraint foreign key (player_id) references player (player_id), constraint foreign key (wicket_taker) references player (player_id));


set foreign_key_checks = 1;

drop table player;
drop table match_stats;
drop table batting_info;
drop table bowling_info;
drop table score_card;
drop table team;