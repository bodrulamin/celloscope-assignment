## cURL commands for testing api
Register user

    curl --location --request POST 'localhost:8080/api/v1/users' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "userId" : 255,
    "password" : "555",
    "mobile" : "01725717136"
    }'

Update user

    curl --location --request PUT 'localhost:8080/api/v1/users' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "userId" : 255,
    "password" : "555",
    "mobile" : "01725717136"
    }'

Login 

    curl --location --request POST 'localhost:8080/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "userId" : 255,
    "password" : "555"
    }'

forgot password: userID and mobile match

    curl --location --request POST 'localhost:8080/forgot' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "userId" : 1,
    "mobile" : "1"
    }'