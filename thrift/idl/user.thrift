namespace java com.claylin.thrift

struct Student{
    1: string name;
    2: i32 age;
    3: i64 id;
}

service StudentService{
    Student getById(1: i64 id);
}