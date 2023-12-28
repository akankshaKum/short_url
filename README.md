URL Shortener:

REST api for shortening an input url


The application exposes an end point /shorten
this allows the user to submit any URL to be shortened
and receive a valid shortened URL that will forward the user's request to the original
URL.
The API accepts a PUT request with the following JSON data
{
“destination”: “valid url”
}
and return the shortened URL or error as JSON.
example (run on Git Bash):
curl -X PUT "http://localhost:8080/shorten" -H "Content-Type: application/json" -d '{"destination": "https://www.google.com/"}'
The above returns - 
http://localhost:8080/1a4c07dd
(1a4c07dd is the generated short code)

The returned URL can be used to redirect the original url.
This is enabled by exposing a get request, which takes the shortened url (the url generated is relative to the url + shortened code) and redirects the user to original URL.
The service accepts a request at /:shortcode: and redirect the user to the original
URL or return an error message as JSON.


The shortened url and original url (destination) are maintained in an h2 database for the purpose of this application.
