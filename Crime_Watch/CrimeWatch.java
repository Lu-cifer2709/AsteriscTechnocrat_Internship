import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CrimeRecord {
    private String caseId;
    private String crimeType;
    private String description;

    public CrimeRecord(String caseId, String crimeType, String description) {
        this.caseId = caseId;
        this.crimeType = crimeType;
        this.description = description;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public String getDescription() {
        return description;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Case ID: " + caseId + "\nCrime Type: " + crimeType + "\nDescription: " + description;
    }
}

public class CrimeWatch {
    private static List<CrimeRecord> crimeRecords = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n\nCrime Record Management System");
            System.out.println("1. Add Crime Record");
            System.out.println("2. Display All Crime Records");
            System.out.println("3. Update Crime Record");
            System.out.println("4. Search Crime Record");
            System.out.println("5. Delete Crime Record");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCrimeRecord();
                    break;
                case 2:
                    displayAllCrimeRecords();
                    break;
                case 3:
                    updateCrimeRecord();
                    break;
                case 4:
                    searchCrimeRecord();
                    break;
                case 5:
                    deleteCrimeRecord();
                    break;
                case 6:
                    System.out.println("\nExiting the Crime Watch. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void addCrimeRecord() {
        System.out.println("Enter Case ID:");
        String caseId = scanner.nextLine();
        System.out.println("Enter Crime Type:");
        String crimeType = scanner.nextLine();
        System.out.println("Enter Description(Date and Time, Location, Suspect, Witness):");
        String description = scanner.nextLine();

        CrimeRecord crimeRecord = new CrimeRecord(caseId, crimeType, description);
        crimeRecords.add(crimeRecord);
        System.out.println("\nCrime record added successfully!");
    }

    private static void displayAllCrimeRecords() {
        if (crimeRecords.isEmpty()) {
            System.out.println("\nNo crime records found.");
        } else {
            System.out.println("\nCrime Records:");
            for (CrimeRecord record : crimeRecords) {
                System.out.println(record);
                System.out.println("----------------------------");
            }
        }
    }

    private static void updateCrimeRecord() {
        if (crimeRecords.isEmpty()) {
            System.out.println("\nNo crime records found.");
            return;
        }

        System.out.print("\nEnter the Case ID to update: ");
        String caseIdToUpdate = scanner.nextLine();

        for (int i = 0; i < crimeRecords.size(); i++) {
            CrimeRecord record = crimeRecords.get(i);
            if (record.getCaseId().equalsIgnoreCase(caseIdToUpdate)) {
                System.out.println("Existing Record:");
                System.out.println(record);
                System.out.println("Enter the updated Crime Type:");
                String updatedCrimeType = scanner.nextLine();
                System.out.println("Enter the updated Description:");
                String updatedDescription = scanner.nextLine();

                record.setCrimeType(updatedCrimeType);
                record.setDescription(updatedDescription);

                System.out.println("\nCrime record updated successfully!");
                return;
            }
        }

        System.out.println("\nCrime record with Case ID '" + caseIdToUpdate + "' not found.");
    }

    private static void searchCrimeRecord() {
        if (crimeRecords.isEmpty()) {
            System.out.println("\nNo crime records found.");
            return;
        }

        System.out.print("\nEnter the Case ID to search: ");
        String caseIdToSearch = scanner.nextLine();

        for (CrimeRecord record : crimeRecords) {
            if (record.getCaseId().equalsIgnoreCase(caseIdToSearch)) {
                System.out.println("\nFound Crime Record:");
                System.out.println(record);
                return;
            }
        }

        System.out.println("\nCrime record with Case ID '" + caseIdToSearch + "' not found.");
    }

    private static void deleteCrimeRecord() {
        if (crimeRecords.isEmpty()) {
            System.out.println("\nNo crime records found.");
            return;
        }

        System.out.print("\nEnter the Case ID to delete: ");
        String caseIdToDelete = scanner.nextLine();

        for (int i = 0; i < crimeRecords.size(); i++) {
            CrimeRecord record = crimeRecords.get(i);
            if (record.getCaseId().equalsIgnoreCase(caseIdToDelete)) {
                crimeRecords.remove(i);
                System.out.println("\nCrime record with Case ID '" + caseIdToDelete + "' deleted successfully!");
                return;
            }
        }

        System.out.println("\nCrime record with Case ID '" + caseIdToDelete + "' not found.");
    }
}
