# MyGallery App - Backend

## Project Description
This application allows a user to login, search through the Art Institute of Chicago’s collection, and save their favorite pieces of artwork in a personal gallery. They can also see the galleries of other users.



## Technologies Used

* Java
* Angular
* PostgreSQL  
* AWS RDBS, EC2
* Docker

## Features

* Register/Login function
* Personal gallery made up of pieces of artwork chosen by the user.
* Ability to scroll through the Art Institute of Chicago's collection 10 pieces of artwork at a time. If a user sees a piece of artowrk they like, they can add it to their gallery by clicking a button.
* A random artwork generator that shows a random piece of artwork from the collection that a user can add to their gallery.
* A link to view other users' galleries.

To-do list:
* Standardize CSS across components
* Fix bugs in database  
* Add a component to update user information
* Add a search function to view results for keywords
* Add a popular artwork function
* Improve view for smaller devices

## Getting Started  
The frontend and backend of the MyGallery app are in different GitHub repositories.

Frontend:
```shell
  git clone https://github.com/critta-montes/ProjectFrontEnd
  cd ProjectFrontEnd
  mvn install
```

Backend:
```shell
  git clone https://github.com/critta-montes/Project2_Backend
  cd Project2_Backend
  mvn install
```
  
## License

This project uses the following license: [GNU Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
