This example describes the below items
1. Define custom exception  by extending RuntimeEception
2. Write a separate class with @RestControllerAdvice on top of class name.
3. write @ExceptionHandler(YourCustomException.class) on top of method like below.
   @ExceptionHandler(EmployeeNotFoundException.class)
   public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
   ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());
   return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
   }
4. Date is formatted with @JSONFormat in the response.

Create Employee :  
![img](https://user-images.githubusercontent.com/33597536/211162037-18f3861c-d5ad-4c9e-9992-fc220c4b1dd1.png)  

Get Employee :  
![img_1](https://user-images.githubusercontent.com/33597536/211162059-940c25e7-3718-452c-ba50-933cefe20b2e.png)  

Update Employee:  
![img_2](https://user-images.githubusercontent.com/33597536/211162074-9f03538f-1c81-41ec-84bd-f8e05b583e8d.png)  

Delete Employee:  
![img_3](https://user-images.githubusercontent.com/33597536/211162090-6499593c-4a2e-431b-a744-021c850e0a94.png)  

Get Employee with invalid id:  
![img_4](https://user-images.githubusercontent.com/33597536/211162104-864e88d4-aa49-49fd-8793-5eab02ff2575.png)  


