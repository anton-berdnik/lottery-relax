# lottery-relax

Application for submitting ballots for lotteries. Winner is selected every midnight.

## Database

DB is hosted at freemysqlhosting.

Database structure:

```sql

create table user_account
(
    id       bigint auto_increment primary key,
    login    varchar(128) null,
    passport varchar(64)  null,
    password varchar(64) null,
    constraint login unique (login)
);

create table lottery
(
    id           bigint auto_increment primary key,
    date_created datetime     null,
    date_finish  date         null,
    status       varchar(64)  null,
    date_updated datetime     null,
    ballot_price int          null,
    prize        varchar(128) null
);

create table lottery_ballot
(
    id              bigint auto_increment primary key,
    lottery_id      bigint     not null,
    user_account_id bigint     not null,
    date_created    datetime   null,
    is_winner       tinyint(1) null,
    constraint lot_blt_lottery
        foreign key (lottery_id) references lottery (id),
    constraint lot_blt_us_acc
        foreign key (user_account_id) references user_account (id)
);
create index lot_blt_winner on lottery_ballot (is_winner);

```

## Usage
After starting LotteryRelaxApplication use next requests:

Create user:
```
curl --location --request POST 'localhost:8080/user/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "login":"someLogin",
    "password":"somePassword",
    "passport":"ZP8111242"
}'
```


Submit ballot:
```
curl --location --request POST 'localhost:8080/lottery-ballot/submit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userAccountId": 58,
    "lotteryId": 12
}'
```

Find lotteries with filter:
```
curl --location --request GET 'localhost:8080/lottery/find-by-filter?finishDate=2024-01-11&status=ACTIVE&page=0&size=5'
```