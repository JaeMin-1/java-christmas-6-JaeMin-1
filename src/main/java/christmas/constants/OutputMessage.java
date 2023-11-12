package christmas.constants;

public enum OutputMessage {
    ORDER_MENU_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n<주문 메뉴>\n"),
    TOTAL_ORDER_AMOUNT("<할인 전 총주문 금액>\n%,d원\n\n"),
    GIVEAWAY_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    TOTAL_BENEFITS("<총혜택 금액>"),
    EXPECTED_PAYMENT_AMOUNT("<할인 후 예상 결제 금액>\n%,d원\n\n"),
    DECEMBER_EVENT_BADGE("<12월 이벤트 배지>\n%s"),
    ONE_CHAMPAGNE("샴페인 1개"),
    ZERO("0원"),
    SANTA("산타"),
    TREE("트리"),
    STAR("별"),
    NOTHING("없음"),
    D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    GIVEAWAY("증정 이벤트");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
