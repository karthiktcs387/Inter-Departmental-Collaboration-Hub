import com.mongodb.client.*;
import org.bson.Document;
import java.util.*;

public class InterDepartmentalCollaborationHub {

    // Node for Department
    static class DepartmentNode {
        String deptId;
        String deptName;
        StudentManagement.StudentDLL studentList;
        DepartmentNode prev, next;

        DepartmentNode(String deptId, String deptName, MongoDatabase db) {
            this.deptId = deptId;
            this.deptName = deptName;

            MongoCollection<Document> coll =
                    db.getCollection(deptName.toLowerCase() + "_students");

            this.studentList = new StudentManagement.StudentDLL(coll);
            this.studentList.loadFromDatabase();
        }
    }

    // Doubly Linked List for Departments
    static class DepartmentDLL {
        DepartmentNode head, tail;
        MongoCollection<Document> deptCollection;
        MongoDatabase db;

        DepartmentDLL(MongoDatabase db) {
            this.db = db;
            this.deptCollection = db.getCollection("departments");
            loadDepartments();
        }

        void loadDepartments() {
            FindIterable<Document> docs = deptCollection.find();
            for (Document d : docs) {
                String id = d.getString("deptId");
                String name = d.getString("deptName");

                DepartmentNode newNode = new DepartmentNode(id, name, db);
                if (head == null)
                    head = tail = newNode;
                else {
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                }
            }
        }

        void addDepartment(String deptId, String deptName) {
            DepartmentNode newDept = new DepartmentNode(deptId, deptName, db);

            if (head == null)
                head = tail = newDept;
            else {
                tail.next = newDept;
                newDept.prev = tail;
                tail = newDept;
            }

            Document doc = new Document("deptId", deptId)
                    .append("deptName", deptName);

            deptCollection.insertOne(doc);
            System.out.println("✅ Department added successfully!");
        }

        void displayDepartments() {
            if (head == null) {
                System.out.println("No departments found.");
                return;
            }

            DepartmentNode current = head;
            while (current != null) {
                System.out.println("Dept ID: " + current.deptId +
                        ", Name: " + current.deptName);
                current = current.next;
            }
        }

        DepartmentNode searchDepartment(String id) {
            DepartmentNode cur = head;
            while (cur != null) {
                if (cur.deptId.equals(id))
                    return cur;
                cur = cur.next;
            }
            return null;
        }

        void deleteDepartment(String id) {
            DepartmentNode dept = searchDepartment(id);

            if (dept != null) {
                if (dept.prev != null) dept.prev.next = dept.next;
                if (dept.next != null) dept.next.prev = dept.prev;

                if (dept == head) head = dept.next;
                if (dept == tail) tail = dept.prev;

                deptCollection.deleteOne(new Document("deptId", id));

                System.out.println("✅ Department deleted successfully!");
            } else {
                System.out.println("Department not found.");
            }
        }
    }

    // Main Method
    public static void main(String[] args) {

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("collaborationHub");

        DepartmentDLL departmentList = new DepartmentDLL(database);
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n=== Inter Departmental Collaboration Hub ===");
            System.out.println("1. Add Department");
            System.out.println("2. Display Departments");
            System.out.println("3. Manage Department Students");
            System.out.println("4. Delete Department");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Department ID: ");
                    String deptId = sc.nextLine();
                    System.out.print("Enter Department Name: ");
                    String deptName = sc.nextLine();
                    departmentList.addDepartment(deptId, deptName);
                }

                case 2 -> departmentList.displayDepartments();

                case 3 -> {
                    System.out.print("Enter Department ID to manage: ");
                    String deptId = sc.nextLine();
                    DepartmentNode dept = departmentList.searchDepartment(deptId);

                    if (dept != null)
                        manageStudentsMenu(dept.studentList, dept.deptName, sc);
                    else
                        System.out.println("Department not found!");
                }

                case 4 -> {
                    System.out.print("Enter Department ID to delete: ");
                    String deptId = sc.nextLine();
                    departmentList.deleteDepartment(deptId);
                }

                case 5 -> System.out.println("Exiting Collaboration Hub...");

                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        mongoClient.close();
        sc.close();
    }

    // Student Management Menu
    private static void manageStudentsMenu(StudentManagement.StudentDLL studentList,
                                           String deptName, Scanner sc) {

        int opt;

        do {
            System.out.println("\n--- Managing Students for Department: " + deptName + " ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter choice: ");

            opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1 -> {
                    System.out.print("Enter Student ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    studentList.addStudent(id, name, age);
                }

                case 2 -> studentList.displayStudents();

                case 3 -> {
                    System.out.print("Enter ID to search: ");
                    String id = sc.nextLine();
                    var s = studentList.searchStudent(id);

                    if (s != null)
                        System.out.println("Found: ID: " + s.id +
                                ", Name: " + s.name + ", Age: " + s.age);
                    else
                        System.out.println("Student not found.");
                }

                case 4 -> {
                    System.out.print("Enter ID to update: ");
                    String id = sc.nextLine();
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    studentList.updateStudent(id, name, age);
                }

                case 5 -> {
                    System.out.print("Enter ID to delete: ");
                    String id = sc.nextLine();
                    studentList.deleteStudent(id);
                }

                case 6 -> System.out.println("Returning to Main Menu...");

                default -> System.out.println("Invalid choice!");
            }

        } while (opt != 6);
    }
}
