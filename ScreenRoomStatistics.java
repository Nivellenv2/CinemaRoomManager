package cinema;

public class ScreenRoomStats {
    private int numSoldTickets = 0;
    private double reservationRatio = 0.00;
    private int currentIncome = 0;
    private int totalIncome = 0;
    private int totalCapacity = 0;

    public ScreenRoomStats(int[][] pricingTable) {
        for (int[] array : pricingTable) {
            for (int num : array) {
                totalIncome += num;
                totalCapacity += 1;
            }
        }
    }

    public void calcSoldTicket(int countSoldTicket, int priceSoldticket) {
        numSoldTickets += countSoldTicket;
        currentIncome += priceSoldticket;
        reservationRatio = (double) numSoldTickets / (double) totalCapacity * 100;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Number of purchased tickets: ")
                .append(numSoldTickets)
                .append("\n")
                .append("Percentage: ")
                .append(reservationRatio)
                .append("%")
                .append("\n")
                .append("Current income: $")
                .append(currentIncome)
                .append("\n")
                .append("Total income: $")
                .append(totalIncome);
        return sb.toString();
    }

    // Solution to meet the test requirement of printing 0.00% instead of 0.0%

    public void printScreenRoomStats() {
        System.out.printf("Number of purchased tickets: %d%n" +
                "Percentage: %.02f%%%n" +
                "Current income: $%d%n" +
                "Total income: $%d%n",
                numSoldTickets, reservationRatio, currentIncome, totalIncome);
    }
}