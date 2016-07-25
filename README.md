# Play/Slick/PostgressSQL CRUD RESTful Service

# Overview
Adapted from scala-play-intro, which is a simple CRUD app developed with Play + Slick. Removed the web forms and made this a pure REST app. Unit tests were changed accordingly to make REST calls and parse REST JSON responses.

# Building & Running Tests

```Bash
$ sbt clean test
```

# Setting Up a Local Postgres Database

## Installing Postgres

```Bash
$  brew install postgres
```

## Creating the Database User:

```Bash
$ psql -h localhost -p 5432 -U <your username> -d postgres
```

On `psql`'s prompt:

```SQL
postgres=# create user admin with password 'admin';
```

##Creating the Database Instance:

On `psql`'s prompt:

```SQL
postgres=# create database scheduler;
```

# Running in Development Mode

```Bash
activator clean run
```

After the service is up, open the [this URL](http://localhost:9000/people) with your browser.
You will be asked to apply an evolution (play will create the database schema).
You should be good to go after that.

# Sending Requests with cURL

Persisting a person:

```Bash
  curl -XPOST -H "Content-Type: application/json"  -d "{\"id\":1,\"name\":\"BoJack Horseman\",\"age\":52,\"lastUpdate\":1469465190275}"  http://localhost:9000/person
```

Retrieving all people:

```Bash
  curl -XGET http://localhost:9000/people
```