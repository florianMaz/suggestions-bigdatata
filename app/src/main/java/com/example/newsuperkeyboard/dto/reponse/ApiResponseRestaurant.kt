package com.example.newsuperkeyboard.dto.reponse


import com.squareup.moshi.Json

data class ApiResponseRestaurant(
    @field:Json(name = "data")
    var `data`: List<Data?>?,
    @field:Json(name = "open_hours_options")
    var openHoursOptions: OpenHoursOptions?,
    @field:Json(name = "paging")
    var paging: Paging?,
    @field:Json(name = "restaurant_availability_options")
    var restaurantAvailabilityOptions: RestaurantAvailabilityOptions?
) {
    data class Data(
        @field:Json(name = "address")
        var address: String?,
        @field:Json(name = "address_obj")
        var addressObj: AddressObj?,
        @field:Json(name = "ancestors")
        var ancestors: List<Ancestor?>?,
        @field:Json(name = "awards")
        var awards: List<Award?>?,
        @field:Json(name = "bearing")
        var bearing: String?,
        @field:Json(name = "booking")
        var booking: Booking?,
        @field:Json(name = "category")
        var category: Category?,
        @field:Json(name = "cuisine")
        var cuisine: List<Cuisine?>?,
        @field:Json(name = "description")
        var description: String?,
        @field:Json(name = "dietary_restrictions")
        var dietaryRestrictions: List<DietaryRestriction?>?,
        @field:Json(name = "distance")
        var distance: String?,
        @field:Json(name = "distance_string")
        var distanceString: String?,
        @field:Json(name = "doubleclick_zone")
        var doubleclickZone: String?,
        @field:Json(name = "establishment_types")
        var establishmentTypes: List<EstablishmentType?>?,
        @field:Json(name = "hours")
        var hours: Hours?,
        @field:Json(name = "is_candidate_for_contact_info_suppression")
        var isCandidateForContactInfoSuppression: Boolean?,
        @field:Json(name = "is_closed")
        var isClosed: Boolean?,
        @field:Json(name = "is_jfy_enabled")
        var isJfyEnabled: Boolean?,
        @field:Json(name = "is_long_closed")
        var isLongClosed: Boolean?,
        @field:Json(name = "latitude")
        var latitude: String?,
        @field:Json(name = "location_id")
        var locationId: String?,
        @field:Json(name = "location_string")
        var locationString: String?,
        @field:Json(name = "longitude")
        var longitude: String?,
        @field:Json(name = "name")
        var name: String?,
        @field:Json(name = "nearest_metro_station")
        var nearestMetroStation: Any?,
        @field:Json(name = "neighborhood_info")
        var neighborhoodInfo: List<NeighborhoodInfo?>?,
        @field:Json(name = "num_reviews")
        var numReviews: String?,
        @field:Json(name = "open_now_text")
        var openNowText: String?,
        @field:Json(name = "parent_display_name")
        var parentDisplayName: String?,
        @field:Json(name = "phone")
        var phone: String?,
        @field:Json(name = "photo")
        var photo: Photo?,
        @field:Json(name = "preferred_map_engine")
        var preferredMapEngine: String?,
        @field:Json(name = "price_level")
        var priceLevel: String?,
        @field:Json(name = "ranking")
        var ranking: String?,
        @field:Json(name = "ranking_category")
        var rankingCategory: String?,
        @field:Json(name = "ranking_denominator")
        var rankingDenominator: String?,
        @field:Json(name = "ranking_geo")
        var rankingGeo: String?,
        @field:Json(name = "ranking_geo_id")
        var rankingGeoId: String?,
        @field:Json(name = "ranking_position")
        var rankingPosition: String?,
        @field:Json(name = "rating")
        var rating: String?,
        @field:Json(name = "raw_ranking")
        var rawRanking: String?,
        @field:Json(name = "reserve_info")
        var reserveInfo: ReserveInfo?,
        @field:Json(name = "subcategory")
        var subcategory: Any?,
        @field:Json(name = "timezone")
        var timezone: String?,
        @field:Json(name = "web_url")
        var webUrl: String?,
        @field:Json(name = "website")
        var website: String?,
        @field:Json(name = "write_review")
        var writeReview: String?
    ) {
        data class AddressObj(
            @field:Json(name = "city")
            var city: String?,
            @field:Json(name = "country")
            var country: String?,
            @field:Json(name = "postalcode")
            var postalcode: String?,
            @field:Json(name = "state")
            var state: Any?,
            @field:Json(name = "street1")
            var street1: String?,
            @field:Json(name = "street2")
            var street2: String?
        )

        data class Ancestor(
            @field:Json(name = "abbrv")
            var abbrv: Any?,
            @field:Json(name = "location_id")
            var locationId: String?,
            @field:Json(name = "name")
            var name: String?,
            @field:Json(name = "subcategory")
            var subcategory: List<Subcategory?>?
        ) {
            data class Subcategory(
                @field:Json(name = "key")
                var key: String?,
                @field:Json(name = "name")
                var name: String?
            )
        }

        data class Award(
            @field:Json(name = "award_type")
            var awardType: String?,
            @field:Json(name = "categories")
            var categories: List<String?>?,
            @field:Json(name = "display_name")
            var displayName: String?,
            @field:Json(name = "images")
            var images: Images?,
            @field:Json(name = "year")
            var year: String?
        ) {
            data class Images(
                @field:Json(name = "large")
                var large: String?,
                @field:Json(name = "small")
                var small: String?
            )
        }

        data class Booking(
            @field:Json(name = "provider")
            var provider: String?,
            @field:Json(name = "url")
            var url: String?
        )

        data class Category(
            @field:Json(name = "key")
            var key: String?,
            @field:Json(name = "name")
            var name: String?
        )

        data class Cuisine(
            @field:Json(name = "key")
            var key: String?,
            @field:Json(name = "name")
            var name: String?
        )

        data class DietaryRestriction(
            @field:Json(name = "key")
            var key: String?,
            @field:Json(name = "name")
            var name: String?
        )

        data class EstablishmentType(
            @field:Json(name = "key")
            var key: String?,
            @field:Json(name = "name")
            var name: String?
        )

        data class Hours(
            @field:Json(name = "timezone")
            var timezone: String?,
            @field:Json(name = "week_ranges")
            var weekRanges: List<List<WeekRange?>?>?
        ) {
            data class WeekRange(
                @field:Json(name = "close_time")
                var closeTime: Int?,
                @field:Json(name = "open_time")
                var openTime: Int?
            )
        }

        data class NeighborhoodInfo(
            @field:Json(name = "location_id")
            var locationId: String?,
            @field:Json(name = "name")
            var name: String?
        )

        data class Photo(
            @field:Json(name = "caption")
            var caption: String?,
            @field:Json(name = "helpful_votes")
            var helpfulVotes: String?,
            @field:Json(name = "id")
            var id: String?,
            @field:Json(name = "images")
            var images: Images?,
            @field:Json(name = "is_blessed")
            var isBlessed: Boolean?,
            @field:Json(name = "published_date")
            var publishedDate: String?,
            @field:Json(name = "uploaded_date")
            var uploadedDate: String?,
            @field:Json(name = "user")
            var user: User?
        ) {
            data class Images(
                @field:Json(name = "large")
                var large: Large?,
                @field:Json(name = "medium")
                var medium: Medium?,
                @field:Json(name = "original")
                var original: Original?,
                @field:Json(name = "small")
                var small: Small?,
                @field:Json(name = "thumbnail")
                var thumbnail: Thumbnail?
            ) {
                data class Large(
                    @field:Json(name = "height")
                    var height: String?,
                    @field:Json(name = "url")
                    var url: String?,
                    @field:Json(name = "width")
                    var width: String?
                )

                data class Medium(
                    @field:Json(name = "height")
                    var height: String?,
                    @field:Json(name = "url")
                    var url: String?,
                    @field:Json(name = "width")
                    var width: String?
                )

                data class Original(
                    @field:Json(name = "height")
                    var height: String?,
                    @field:Json(name = "url")
                    var url: String?,
                    @field:Json(name = "width")
                    var width: String?
                )

                data class Small(
                    @field:Json(name = "height")
                    var height: String?,
                    @field:Json(name = "url")
                    var url: String?,
                    @field:Json(name = "width")
                    var width: String?
                )

                data class Thumbnail(
                    @field:Json(name = "height")
                    var height: String?,
                    @field:Json(name = "url")
                    var url: String?,
                    @field:Json(name = "width")
                    var width: String?
                )
            }

            data class User(
                @field:Json(name = "member_id")
                var memberId: String?,
                @field:Json(name = "type")
                var type: String?,
                @field:Json(name = "user_id")
                var userId: Any?
            )
        }

        data class ReserveInfo(
            @field:Json(name = "api_bookable")
            var apiBookable: Boolean?,
            @field:Json(name = "banner_text")
            var bannerText: Any?,
            @field:Json(name = "bestoffer")
            var bestoffer: Bestoffer?,
            @field:Json(name = "booking_partner_id")
            var bookingPartnerId: String?,
            @field:Json(name = "button_text")
            var buttonText: String?,
            @field:Json(name = "disclaimer_text")
            var disclaimerText: Any?,
            @field:Json(name = "id")
            var id: String?,
            @field:Json(name = "provider")
            var provider: String?,
            @field:Json(name = "provider_img")
            var providerImg: String?,
            @field:Json(name = "racable")
            var racable: Boolean?,
            @field:Json(name = "timeslot_offers")
            var timeslotOffers: Any?,
            @field:Json(name = "timeslots")
            var timeslots: Any?,
            @field:Json(name = "url")
            var url: String?
        ) {
            data class Bestoffer(
                @field:Json(name = "description")
                var description: String?,
                @field:Json(name = "discount_percent")
                var discountPercent: String?,
                @field:Json(name = "displayable")
                var displayable: Boolean?,
                @field:Json(name = "exclusions")
                var exclusions: Any?,
                @field:Json(name = "id")
                var id: String?,
                @field:Json(name = "is_fixedmenu")
                var isFixedmenu: Boolean?,
                @field:Json(name = "is_specialoffer")
                var isSpecialoffer: Boolean?,
                @field:Json(name = "title")
                var title: String?
            )
        }
    }

    data class OpenHoursOptions(
        @field:Json(name = "closed_count")
        var closedCount: String?,
        @field:Json(name = "current_value")
        var currentValue: String?,
        @field:Json(name = "is_set")
        var isSet: Boolean?,
        @field:Json(name = "low_coverage_primary_message")
        var lowCoveragePrimaryMessage: String?,
        @field:Json(name = "low_coverage_secondary_message")
        var lowCoverageSecondaryMessage: String?,
        @field:Json(name = "open_count")
        var openCount: String?,
        @field:Json(name = "timezone")
        var timezone: String?,
        @field:Json(name = "unsure_count")
        var unsureCount: String?
    )

    data class Paging(
        @field:Json(name = "results")
        var results: String?,
        @field:Json(name = "total_results")
        var totalResults: String?
    )

    data class RestaurantAvailabilityOptions(
        @field:Json(name = "datestring")
        var datestring: String?,
        @field:Json(name = "day")
        var day: String?,
        @field:Json(name = "hour")
        var hour: String?,
        @field:Json(name = "is_default")
        var isDefault: Boolean?,
        @field:Json(name = "is_set")
        var isSet: Boolean?,
        @field:Json(name = "minute")
        var minute: String?,
        @field:Json(name = "month")
        var month: String?,
        @field:Json(name = "people")
        var people: String?,
        @field:Json(name = "people_options")
        var peopleOptions: PeopleOptions?,
        @field:Json(name = "racable")
        var racable: Boolean?,
        @field:Json(name = "time_options")
        var timeOptions: TimeOptions?,
        @field:Json(name = "year")
        var year: String?
    ) {
        data class PeopleOptions(
            @field:Json(name = "options")
            var options: List<Option?>?,
            @field:Json(name = "selected_option")
            var selectedOption: SelectedOption?
        ) {
            data class Option(
                @field:Json(name = "display")
                var display: String?,
                @field:Json(name = "selected")
                var selected: Any?,
                @field:Json(name = "value")
                var value: String?
            )

            data class SelectedOption(
                @field:Json(name = "display")
                var display: String?,
                @field:Json(name = "selected")
                var selected: Boolean?,
                @field:Json(name = "value")
                var value: String?
            )
        }

        data class TimeOptions(
            @field:Json(name = "options")
            var options: List<Option?>?,
            @field:Json(name = "selected_option")
            var selectedOption: SelectedOption?
        ) {
            data class Option(
                @field:Json(name = "display")
                var display: String?,
                @field:Json(name = "selected")
                var selected: Any?,
                @field:Json(name = "value")
                var value: String?
            )

            data class SelectedOption(
                @field:Json(name = "display")
                var display: String?,
                @field:Json(name = "selected")
                var selected: Boolean?,
                @field:Json(name = "value")
                var value: String?
            )
        }
    }
}