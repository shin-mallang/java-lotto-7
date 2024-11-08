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
        Winnings winnings = new Winnings(lottos);
        WinningNumbers winningNumbers = inputWinningNumbers();
        winnings.matchAll(winningNumbers);
        printWinningStatistics(winnings);
    }

    private static Lottos purchaseLotto() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        System.out.println("구입금액을 입력해 주세요");
        int amount = Integer.parseInt(Console.readLine());
        Lottos lottos = Lottos.purchase(amount, generator);
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

    private static void printWinningStatistics(Winnings winnings) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Winning winning : Winning.values()) {
            if (winning == Winning.NONE) {
                continue;
            }
            int count = winnings.getCount(winning);
            System.out.println(createWinningMessage(winning, count));
        }
        System.out.println();
        System.out.println("총 수익률은 %.2f".formatted(winnings.getRateOfReturn()) + "%입니다.");
    }

    private static String createWinningMessage(Winning winning, int count) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        String matchCount = "%d개 일치 ".formatted(winning.getMatchCount());
        String bonusMatch = winning.isBonusMatch()
                ? ", 보너스 볼 일치"
                : "";
        String amountAndCount = "(%s원) - %d개".formatted(
                numberFormat.format(winning.getWinningAmount())
                , count);
        return matchCount + bonusMatch + amountAndCount;
    }
}
