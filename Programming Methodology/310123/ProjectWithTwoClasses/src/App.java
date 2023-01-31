import es.employee.*;

public class App {
    public static void main(String[] args){
        BasePlusCommissionEmployee employeeBP = new BasePlusCommissionEmployee("Bob", "Lewis", "333-33-3333", 5000, .04,
                500);
        CommissionEmployee employee = new CommissionEmployee("Sue", "Jones", "222-22-2222", 10000, .06);

        CommissionEmployee[] employeeVector = { employee,
                new CommissionEmployee("Tom", "Tomthsom", "222-022-2222", 6748, 0.04),
                new CommissionEmployee("Tim", "Smith", "222-022-2222", 4789, 0.05),
                new CommissionEmployee("Max", "Thorton", "222-022-2222", 7845, 0.07),
                new CommissionEmployee("Eva", "Lia", "222-022-2222", 3475, 0.07)
        };
        BasePlusCommissionEmployee[] employeeBPVector = { employeeBP,
                new BasePlusCommissionEmployee("Joseph", "Goodwill", "333-33-3333", 5500, 0.06, 545),
                new BasePlusCommissionEmployee("Hermione", "Granger", "444-44-4444", 4750, 0.09, 599),
                new BasePlusCommissionEmployee("Ron", "Weasley", "314-15-9265", 5250, 0.04, 502),
                new BasePlusCommissionEmployee("Severus", "Snape", "555-55-5555", 5555, .05, 555)
        };
        //First we print the unsorted arrays
        printArray(employeeVector);
        printArray(employeeBPVector);

        //Then we print the sorted arrays
        printArray(sort(employeeVector));
        printArray(sort(employeeBPVector));

    }

    public static CommissionEmployee[] sort(CommissionEmployee[] array) {
        CommissionEmployee aux = null;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i].earnings() > array[i + 1].earnings()) {
                    aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                    swapped = true;
                }
            }
        }
        return array;
    }
    public static BasePlusCommissionEmployee[] sort(BasePlusCommissionEmployee[] array) {
        BasePlusCommissionEmployee aux = null;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i].earnings() > array[i + 1].earnings()) {
                    aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                    swapped = true;
                }
            }
        }
        return array;
    }
    public static void printArray(CommissionEmployee[] array){
        System.out.println("Commission employees: ");
        for(CommissionEmployee i : array){
            System.out.println("\t" + i);
        }
    }
    public static void printArray(BasePlusCommissionEmployee[] array){
        System.out.println("Base-Salaried Commission employees: ");
        for(BasePlusCommissionEmployee i : array){
            System.out.println("\t" + i);
        }
    }
}
