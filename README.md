# PA165-LibraryManager
Semestral project for PA165

Library Manager is project of information system for a public library. The system will provide information about books possessed by library, their members, as well as individual loaned items of every member. Books are divided into collections. Book may belong to many collections. Library member can have many loans, whilst one lone is bound to single book. Loans keep track of returned state of book. There will be two types of users - admin and member. Every user can log in to system with his user credentials. Member can search for books by specific criteria and view them. Naturally member is capable of making new loans and manage existing ones. In addition to member privileges, admin can add new books and create collections.


# Rest api
Our project provide Rest api for Book entity.

Rest api provide this functions:
 - getAll()
 - getBook()
 - createBook()
 - changeBookState()

## How to use these functions:

getAll():  
Available on address: http://localhost:8080/pa165/rest/books as GET request.  
You can use this curl: curl -X GET http://localhost:8080/pa165/rest/books/

getBook():  
Available on address: http://localhost:8080/pa165/rest/books/{id of book} as GET request.  
You can use this curl: curl -X GET http://localhost:8080/pa165/rest/books/1

createBook():  
Available on address: http://localhost:8080/pa165/rest/books as POST request.  
You can use this curl: curl -X POST -i -H "Content-Type: application/json" --data '{"name":"name","authorName":"author","isbn":196,"collectionIds":[1,2,3]}' http://localhost:8080/pa165/rest/books/

changeBookState():  
Available on address: http://localhost:8080/pa165/rest/books/{id of book}/state as PUT request.  
You can use this curl: curl -X PUT -i --data LIGHT_DAMAGE http://localhost:8080/pa165/rest/books/1/state

deleteBook():  
Available on address: http://localhost:8080/pa165/rest/books/{id of book} as DELETE request.  
You can use this curl: curl -X DELETE i http://127.0.0.1:8080/pa165/rest/books/1
