# Checkout + Order

Địa chỉ giao hàng, đặt COD, vòng đời đơn, trừ tồn.

## Địa chỉ

- User quản lý sổ địa chỉ: recipient, phone, line1, city, district, isDefault.
- Checkout nhận `addressId` **của chính user**, copy **snapshot** vào order (không FK sống — sửa address không đổi đơn cũ).
- Địa chỉ người khác / không tồn tại → 404.

| Method | Path |
|--------|------|
| GET/POST | `/api/v1/me/addresses` |
| PUT/DELETE | `/api/v1/me/addresses/{id}` |

## Thanh toán MVP

Chỉ **COD**. Không payment intent, không webhook. FE ghi “Pay with cash on delivery”. Chống double-submit: disable nút lúc submitting (BE chưa làm Idempotency-Key).

## Order

Tables: `orders` + `order_items`.

Snapshot order: recipient, phone, line1, city, district, `total_amount`.  
Snapshot item: sku_id, sku_code, product_name, unit_price, quantity, line_total.

### Status

| Status | Ai | Ý nghĩa |
|--------|----|---------|
| `PENDING_CONFIRMATION` | system lúc checkout | Chờ admin |
| `CONFIRMED` | admin | Nhận đơn, thu COD khi giao |
| `SHIPPED` | admin | Đã gửi |
| `DELIVERED` | admin | Xong; **commit** inventory |
| `CANCELLED` | user (chỉ PENDING) hoặc admin | **release** reserve |

```text
PENDING_CONFIRMATION → CONFIRMED | CANCELLED
CONFIRMED → SHIPPED | CANCELLED
SHIPPED → DELIVERED
DELIVERED, CANCELLED → terminal
```

Không hủy `SHIPPED` / `DELIVERED` trong MVP.

### Checkout (1 transaction)

1. Cart rỗng → `CART_EMPTY`
2. Load address thuộc user
3. Mỗi item: `InventoryService.reserve`
4. Tạo order `PENDING_CONFIRMATION` + items (giá tại thời điểm checkout)
5. Clear cart

User đọc đơn: 404 nếu không phải của mình (không 403).  
Cancel user: chỉ `PENDING_CONFIRMATION`.

## API

| Method | Path | Auth |
|--------|------|------|
| POST | `/api/v1/checkout` | user — `{ addressId }` |
| GET | `/api/v1/orders` | user, của mình |
| GET | `/api/v1/orders/{id}` | user, của mình |
| POST | `/api/v1/orders/{id}/cancel` | user |
| GET | `/api/v1/admin/orders` | admin |
| GET | `/api/v1/admin/orders/{id}` | admin |
| POST | `/api/v1/admin/orders/{id}/status` | admin — `{ status }` |

ErrorCode thêm: `CART_EMPTY`, `INSUFFICIENT_STOCK`, `INVALID_ORDER_TRANSITION`, `SKU_NOT_AVAILABLE`.

## Checklist BE

- [ ] `customer_addresses` + CRUD scoped theo user
- [ ] `orders` / `order_items` + checkout transaction
- [ ] User list/detail/cancel
- [ ] Admin list/detail/status đúng transition
- [ ] Delivered commit; cancel release; stock fail thì không tạo order, cart giữ nguyên

## Checklist FE

- [ ] `/account/addresses` CRUD (Signal Forms)
- [ ] `/checkout`: chọn address, COD, disable double-click, empty cart không submit
- [ ] Success → `/orders/:id`
- [ ] `/orders` + detail + Cancel khi PENDING
- [ ] Admin `/admin/orders` + nút status hợp lệ

## Later

Idempotency-Key, VNPay/Stripe, refund, invoice, email xác nhận.
