# Checklist tính năng — MVP

Tick khi **BE và FE** của dòng đó đều xong (trừ dòng ghi rõ chỉ một phía). Chi tiết rule: file trong `features/`.

**Hiện trạng (2026-09-13):** login JWT + admin tạo product/SKU/warehouse. Chưa browse public, cart, checkout, order.

## Phase 0 — Identity + vỏ storefront


|     | Tính năng                                | BE                       | FE                                       |
| --- | ---------------------------------------- | ------------------------ | ---------------------------------------- |
|     | Register gán `ROLE_USER`                 | [ ]                      | —                                        |
|     | `GET /api/v1/auth/me`                    | [ ]                      | [ ] (optional; header có thể decode JWT) |
|     | Warehouse chỉ `ROLE_ADMIN`               | [ ]                      | —                                        |
|     | Trang register                           | —                        | [ ]                                      |
|     | Home `/` **public** (bỏ `authGuard`)     | [ ] permit `/catalog/`** | [ ]                                      |
|     | Header: login/register/logout/cart/admin | —                        | [ ]                                      |
|     | Sidebar admin có `routerLink` + logout   | —                        | [ ]                                      |


→ [identity.md](./features/identity.md) · [storefront-admin.md](./features/storefront-admin.md)

## Phase 1 — Catalog


|     | Tính năng                                        | BE  | FE                         |
| --- | ------------------------------------------------ | --- | -------------------------- |
|     | CRUD category + brand                            | [ ] | [ ] admin                  |
|     | Product GET/PUT; status; FK category/brand       | [ ] | [ ] admin form             |
|     | Create product gắn attribute + ảnh URL + skuCode | [ ] | [ ]                        |
|     | Public list/detail category + product            | [ ] | [ ] home, `/products`, PDP |


→ [catalog.md](./features/catalog.md)

## Phase 2 — Inventory


|     | Tính năng                              | BE  | FE                |
| --- | -------------------------------------- | --- | ----------------- |
|     | `available = quantity - reserved`      | [ ] | [ ] hiện trên PDP |
|     | Reserve / release / commit + lock dòng | [ ] | —                 |
|     | Unique `(sku_id, warehouse_id)`        | [ ] | —                 |


→ [inventory.md](./features/inventory.md)

## Phase 3 — Cart


|     | Tính năng                                             | BE  | FE          |
| --- | ----------------------------------------------------- | --- | ----------- |
|     | Giỏ 1 user; upsert / xóa / clear                      | [ ] | [ ]         |
|     | Từ chối SKU inactive / hết hàng                       | [ ] | [ ] báo lỗi |
|     | Add to cart từ PDP (chưa login → `/login?returnUrl=`) | —   | [ ]         |


→ [cart.md](./features/cart.md)

## Phase 4 — Checkout + Order


|     | Tính năng                                             | BE  | FE  |
| --- | ----------------------------------------------------- | --- | --- |
|     | Sổ địa chỉ `/me/addresses`                            | [ ] | [ ] |
|     | Checkout COD 1 transaction + snapshot giá/địa chỉ     | [ ] | [ ] |
|     | User: list/detail/cancel (chỉ `PENDING_CONFIRMATION`) | [ ] | [ ] |
|     | Admin: list/detail + đổi status hợp lệ                | [ ] | [ ] |
|     | Không đọc được đơn người khác                         | [ ] | —   |


→ [checkout-order.md](./features/checkout-order.md)

## Phase 5 — Khóa MVP


|     | Tiêu chí                                                                             |
| --- | ------------------------------------------------------------------------------------ |
|     | Register → login → browse → cart → address → checkout → order `PENDING_CONFIRMATION` |
|     | Cancel pending: `reserved` giảm                                                      |
|     | Admin CONFIRMED → SHIPPED → DELIVERED: delivered **commit** stock                    |
|     | User không vào `/admin`                                                              |
|     | `./gradlew test` + `ng build`                                                        |




## Later (không làm trong MVP)


|     | Tính năng                                |
| --- | ---------------------------------------- |
|     | Guest cart, refresh token, quên mật khẩu |
|     | VNPay / Stripe                           |
|     | Coupon, review, email, carrier tracking  |
|     | Elasticsearch, upload file               |


