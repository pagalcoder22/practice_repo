drop table if exists chat_room;
create table chat_room (
    id bigint not null auto_increment,
    description varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
) engine=InnoDB;

drop table if exists chatroom_participants;
create table chatroom_participants (
    chatroom_id bigint,
    id bigint not null auto_increment,
    participant_id bigint,
    primary key (id),
    foreign key (chatroom_id) references chat_room (id),
    foreign key (participant_id) references users (id)
) engine=InnoDB;

drop table if exists message;
create table message (
    chatroom_id bigint not null,
    created_at datetime(6) not null,
    id bigint not null auto_increment,
    sender_id bigint not null,
    content varchar(255) not null,
    file_name varchar(255),
    emoji_type enum ('THUMB_UP','LOVE','CRYING','SURPRISED'),
    message_type enum ('TEXT','IMAGE','VIDEO','EMOJI'),
    primary key (id),
    foreign key (chatroom_id) references chat_room (id),
    foreign key (sender_id) references users (id)
) engine=InnoDB;

drop table if exists user_profile;
create table user_profile (
    id bigint not null auto_increment,
    user_id bigint,
    bio varchar(255),
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    phone_number varchar(255),
    profile_picture_url varchar(255),
    user_name varchar(255),
    primary key (id),
    unique (user_id),
    foreign key (user_id) references users (id)
) engine=InnoDB;

drop table if exists users;
create table users (
    id bigint not null auto_increment,
    primary key (id)
) engine=InnoDB;