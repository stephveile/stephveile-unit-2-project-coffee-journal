export default class EntryDTO {
    constructor(shopId, drinkOrder, rating, review, wouldRecommend, visitDate, userId) {
        this.shopId = shopId;
        this.drinkOrder = drinkOrder;
        this.rating = rating;
        this.review = review;
        this.wouldRecommend = wouldRecommend;
        this.visitDate = visitDate;
        this.userId = userId;
    }
}
