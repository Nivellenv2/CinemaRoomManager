package cinema;

public class ScreenRoom {
    private String[][] seatReservation;
    private int[][] pricingTable;
    private ScreenRoomStats screenRoomStats;

    public ScreenRoom(int rows, int seatsPerRow) {
        seatReservation = new String[rows][seatsPerRow];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatReservation[i][j] = "S";
            }
        }

        createPricingTable(rows, seatsPerRow, 10, 8);

        screenRoomStats = new ScreenRoomStats(pricingTable);
    }

    public void createPricingTable(int rows, int seatsPerRow, int frontPrice, int backPrice) {
        pricingTable = new int[rows][seatsPerRow];
        if (rows * seatsPerRow <= 60) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seatsPerRow; j++) {
                    pricingTable[i][j] = frontPrice;
                }
            }
        } else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seatsPerRow; j++) {
                    if (i <= rows / 2 - 1) {
                        pricingTable[i][j] = frontPrice;
                    } else {
                        pricingTable[i][j] = backPrice;
                    }
                }
            }
        }
    }

    public void printCinemaRoom() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 0; i < seatReservation[0].length; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        for (int i = 0; i < seatReservation.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatReservation[i].length; j++) {
                System.out.print(seatReservation[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isSeatExisting(int row, int seat) {
        return !(row < 1 || row > seatReservation.length  || seat < 1 || seat > seatReservation[0].length);
    }

    public boolean isSeatAvailable(int row, int seat) {
        return "S".equals(seatReservation[row - 1][seat - 1]);
    }

    public void reserveSeat(int row, int seat) {
        seatReservation[row - 1][seat - 1] = "B";
        screenRoomStats.calcSoldTicket(1, pricingTable[row - 1][seat - 1]);
    }

    public void printSeatPrice(int row, int seat) {
        System.out.println("Ticket price: $" + pricingTable[row - 1][seat - 1]);
    }

    public ScreenRoomStats getScreenRoomStats() {
        return screenRoomStats;
    }
}