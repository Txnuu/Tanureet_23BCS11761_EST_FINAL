import java.sql.*;
import java.util.Scanner;

public class Tanureet_23BCS11761_EST_FINAL {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/companydb";
        String user = "root";
        String password = "#T@nureet124";

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            while (true) {
                System.out.println("\n1. Create Product");
                System.out.println("2. Read Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        int pid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Product Name: ");
                        String pname = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        String insertQuery = "INSERT INTO Product VALUES (?, ?, ?, ?)";
                        PreparedStatement ps1 = con.prepareStatement(insertQuery);
                        ps1.setInt(1, pid);
                        ps1.setString(2, pname);
                        ps1.setDouble(3, price);
                        ps1.setInt(4, qty);
                        ps1.executeUpdate();
                        con.commit();
                        System.out.println("Product inserted successfully!");
                        break;

                    case 2:
                        String selectQuery = "SELECT * FROM Product";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(selectQuery);
                        System.out.println("\nProductID | ProductName | Price | Quantity");
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getDouble(3) + " | " + rs.getInt(4));
                        }
                        break;

                    case 3:
                        System.out.print("Enter Product ID to update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Product Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new Price: ");
                        double newPrice = sc.nextDouble();
                        System.out.print("Enter new Quantity: ");
                        int newQty = sc.nextInt();
                        String updateQuery = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
                        PreparedStatement ps2 = con.prepareStatement(updateQuery);
                        ps2.setString(1, newName);
                        ps2.setDouble(2, newPrice);
                        ps2.setInt(3, newQty);
                        ps2.setInt(4, uid);
                        int updated = ps2.executeUpdate();
                        if (updated > 0) {
                            con.commit();
                            System.out.println("Product updated successfully!");
                        } else {
                            con.rollback();
                            System.out.println("No product found with that ID.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Product ID to delete: ");
                        int did = sc.nextInt();
                        String deleteQuery = "DELETE FROM Product WHERE ProductID=?";
                        PreparedStatement ps3 = con.prepareStatement(deleteQuery);
                        ps3.setInt(1, did);
                        int deleted = ps3.executeUpdate();
                        if (deleted > 0) {
                            con.commit();
                            System.out.println("Product deleted successfully!");
                        } else {
                            con.rollback();
                            System.out.println("No product found with that ID.");
                        }
                        break;

                    case 5:
                        con.close();
                        sc.close();
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

