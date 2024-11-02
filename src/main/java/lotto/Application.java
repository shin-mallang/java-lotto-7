package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        LottoSeller lottoSeller = new LottoSeller();

        System.out.println("구입금액을 입력해 주세요");
        int amount = Integer.parseInt(Console.readLine());
        List<Lotto> lottos = lottoSeller.sell(amount);
        System.out.println("%s개를 구입했습니다.".formatted(lottos.size()));
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }

        System.out.println("당첨 번호를 입력해 주세요.");
        Set<Integer> winningNumberInputs = Arrays.stream(Console.readLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberInputs);
        System.out.println("보너스 번호를 입력해 주세요.");
        LottoNumber bonusNumber = new LottoNumber(Integer.parseInt(Console.readLine()));;
        winningNumbers.withBonusNumber(bonusNumber);

        Map<Winning, Integer> winnings = new HashMap<>();
        for (Lotto lotto : lottos) {
            Winning winning = winningNumbers.match(lotto);
            Integer count = winnings.getOrDefault(winning, 0);
            winnings.put(winning, count + 1);
        }

        System.out.println("당첨 통계");
        System.out.println("---");
        for (Winning winning : Winning.values()) {
            Integer count = winnings.getOrDefault(winning, 0);
            if (winning == Winning.SECOND) {
                System.out.println("%d개 일치, 보너스 볼 일치 (%s)원 - %d개".formatted(
                        winning.getMatchCount(),
                        numberFormat.format(winning.getWinningAmount()),
                        count
                ));
            }
            if (winning == Winning.NONE) {
                continue;
            } else {
                System.out.println("%d개 일치 (%s)원 - %d개".formatted(
                        winning.getMatchCount(),
                        numberFormat.format(winning.getWinningAmount()),
                        count
                ));
            }
        }
        System.out.println("총 수익률은 %d\\%입니다.");
    }
}
