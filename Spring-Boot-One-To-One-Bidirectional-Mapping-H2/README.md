1. Here i treated Laptop as parent entity
2. Employee is a child entity
3. Because foreign key(laptop_id) is added to Employee table. So this acts as owning side
4. Laptop (Parent entity) acts as non owning side 
5  @Table & @Column & @JoinColumn are Optional. If you want to customize the names, then you can use them
6  You need to set parent entity in child entity and vice versa
7  Adding cascade = Cascade.ALL at parent entiy is recomended. However, you can add at child entity too
8  With cascade, we can save either parent or child entity. It will create the other one automatically. No need to save both entities separately. 
9  However you do, it will first create parent table record first and tehn child table record with parent id as foreign key.
10. By default @OneToOne is Eager loaded. Add fetch= FetchType.LAZY to enable lazy loading.
11. Lazy load works fine from child entity to parent entiy (Employee to Laptop).
12. But not from parent to entity (Laptop to Employee). Need to do some workaround for the same to enable lazy loading.