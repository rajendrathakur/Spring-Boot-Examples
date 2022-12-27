![manytomay_Representation](https://user-images.githubusercontent.com/33597536/209601188-ee908ab7-9981-4721-a36d-611558339519.png)

Case-1:
If we keep @ManyToMany only in Model entity like below
 @ManyToMany(cascade = CascadeType.PERSIST )
  private Set<Manufacturer> manufacturers = new HashSet<>();

Then below tables are created:
manufacturer
model
model_manufacturers  
  
Case-2:
  If we keep @ManyToMany only in Manufacturer entity like below
 @ManyToMany(cascade = CascadeType.PERSIST )
  private Set<Model> models = new HashSet<>();

Then below tables are created:
manufacturer
model
manufacturer_models
  
Case-3:
 If we keep @ManyToMany both in Model & Manufacturer entities without mappedBy attribute.

 @ManyToMany(cascade = CascadeType.PERSIST )
 private Set<Model> models = new HashSet<>();
  
 ManyToMany(cascade = CascadeType.PERSIST )
 private Set<Manufacturer> manufacturers = new HashSet<>();
  
Then below tables are created:
manufacturer
model
manufacturer_models
model_manufacturers
  
Case-4:
If we keep @ManyToMany both in Model & Manufacturer entities with mappedBy attribute in Manufacturer entity like below
@ManyToMany(mappedBy = "manufacturers")
private Set<Model> models = new HashSet<>();
  
@ManyToMany(cascade = CascadeType.PERSIST )
private Set<Manufacturer> manufacturers = new HashSet<>();

Then below tables are created:
manufacturer  (Non Owning Side)
model (Owning Side)
model_manufacturers

Key Points:
Always bidirectional association is  a recommended approach.
Add mappedBy attribute and make the entity as non owning side
Other side will act as owning side
If we try to delete a record at owning side without cascading, then hibernate  will first delete the respective associations from join table automatically  and finally delete the record from owning side.
If we try to delete a record at non owning side without cascading, then hibernate will not delete the respective associations from join table automatically. So we need to delete the same manually.
  
We have observed the below bad effects of cascading when we keep cascade = Cascade.REMOVE at non owning side and try to delete a record at non owning side.
      Deleting a record at non owning side and its children even if they are associated to other parent. Cascading removing the association of its children with other parent and there by deleting the children too.
If we keep cascade = Cascade.REMOVE at both non owning side & owning side as well, then try to delete a record at non owning side.
      Hibernate will delete all records of its chained objects til the bottom record which is too bad.
           
Best way is do not use cascade = Cascade.REMOVE at both sides. Write custom logic to remove the children
      Verify each child record whether it has any reference with any other parent or not
    . If yes, then only delete the association with that child only from the join table. Do not remove the child by disassociating the associations with other parents. 
      If no, then delete the child record.
      Finally delete the record which you are trying to remove at either owning /non owning side.
     









