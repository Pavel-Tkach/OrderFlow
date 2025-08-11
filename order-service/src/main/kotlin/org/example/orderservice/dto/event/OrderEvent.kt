package org.example.orderservice.dto.event

import java.time.OffsetDateTime

open class OrderEvent(
    val eventTime: OffsetDateTime = OffsetDateTime.now(),
) {

}
