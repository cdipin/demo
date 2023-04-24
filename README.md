# demo
Added 2 APIs, one for ADMIN and one for USER.


cURLs

ADMIN Request
curl --location 'http://localhost:8080/api/v1/bank-statements' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--header 'Content-Type: application/json' \
--data '{
    "accountId": 1,
    "minAmount": 10,
    "maxAmount" : 100
}'

USER Request
curl --location --request GET 'http://localhost:8080/api/v1/bank-statements' \
--header 'Authorization: Basic dXNlcjp1c2Vy' \
--header 'Content-Type: application/json'
