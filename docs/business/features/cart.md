# Cart

Giỏ hàng của user đã login. Add-to-cart **bắt buộc đăng nhập** (không guest cart trong MVP).

## Rule

- 1 cart / user (`user_id` unique). `user_id` là `BIGINT` (User.id hiện là Long).
- Item: `skuId` + `quantity`. Unique `(cart_id, sku_id)`.
- `PUT` quantity `>= 1` = upsert; `0` = xóa dòng.
- Tổng dòng tính theo **giá SKU hiện tại** (chưa snapshot). Snapshot lúc checkout.
- Từ chối SKU không `ACTIVE` (`SKU_NOT_AVAILABLE`) hoặc `available < qty` (`INSUFFICIENT_STOCK`).
- Checkout xong **xóa cart**.

## API

| Method | Path | Body |
|--------|------|------|
| GET | `/api/v1/cart` | |
| PUT | `/api/v1/cart/items` | `{ skuId, quantity }` |
| DELETE | `/api/v1/cart` | clear |

Auth: authenticated (user mua được; admin cũng được nếu muốn test).

`CartDTO`: items (skuId, skuCode, productName, unitPrice, quantity, lineTotal, availableQuantity) + `total`.

## Checklist BE

- [ ] Tables `carts`, `cart_items`
- [ ] GET / PUT / DELETE
- [ ] Reject inactive + thiếu tồn

## Checklist FE

- [ ] `/cart` (authGuard): sửa qty, xóa, total, CTA checkout
- [ ] PDP Add to cart: chưa login → `/login?returnUrl=/products/:id`
- [ ] Header link Cart

## Later

Guest cart + merge khi login.
