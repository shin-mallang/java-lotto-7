package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Lottos lottos = purchaseLotto();
        WinningNumbers winningNumbers = inputWinningNumbers();
        Winnings winnings = new Winnings(lottos);
        printWinningStatistics(winnings, winningNumbers);
    }

    private static Lottos purchaseLotto() {
        System.out.println("구입금액을 입력해 주세요");
        int amount = Integer.parseInt(Console.readLine());
        Lottos lottos = Lottos.purchase(amount);
        System.out.println("%s개를 구입했습니다.".formatted(lottos.getLottos().size()));
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
        return lottos;
    }

    private static WinningNumbers inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        Set<LottoNumber> winningNumberInputs = Arrays.stream(Console.readLine().split(","))
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberInputs);
        System.out.println("보너스 번호를 입력해 주세요.");
        LottoNumber bonusNumber = new LottoNumber(Console.readLine());
        winningNumbers.withBonusNumber(bonusNumber);
        return winningNumbers;
    }

    private static void printWinningStatistics(Winnings winnings, WinningNumbers winningNumbers) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        winnings.matchAll(winningNumbers);
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Winning winning : Winning.values()) {
            int count = winnings.getCount(winning);
            printWinningResult(winning, numberFormat, count);
        }
        System.out.println("총 수익률은 %d\\%입니다.".formatted(winnings.getRateOfReturn()));
    }

    private static void printWinningResult(Winning winning, NumberFormat numberFormat, int count) {
        if (winning == Winning.SECOND) {
            System.out.println("%d개 일치, 보너스 볼 일치 (%s)원 - %d개".formatted(
                    winning.getMatchCount(),
                    numberFormat.format(winning.getWinningAmount()),
                    count
            ));
        } else if (winning != Winning.NONE) {
            System.out.println("%d개 일치 (%s)원 - %d개".formatted(
                    winning.getMatchCount(),
                    numberFormat.format(winning.getWinningAmount()),
                    count
            ));
        }
    }
}
