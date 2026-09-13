# Storefront + Admin (UI)

Áp dụng trên `ecommerce-frontend`. API bám các file feature backend.

## Đã có


| Route              | Guard              | UI         |
| ------------------ | ------------------ | ---------- |
| `/login`           | guest              | Login thật |
| `/`                | **auth — phải bỏ** | Home stub  |
| `/admin/dashboard` | admin              | Stub       |
| Còn lại            |                    | Chưa có    |


## Rule UI

- Form mới: Signal Forms, không FormBuilder.
- HTTP: `apiUrl('/api/v1/...')`.
- `/`, `/products`, `/products/:id` **không** `authGuard`.
- `/cart`, `/checkout`, `/account/**`, `/orders/**`: `authGuard` + `returnUrl`.
- `/admin/**`: `roleGuard([ROLE_ADMIN])`.
- Admin mặc định sau login: `/admin/dashboard`; user: `/`.

## Routes đích


| Path                            | Guard | Việc                    |
| ------------------------------- | ----- | ----------------------- |
| `/login`                        | guest | existing                |
| `/register`                     | guest | tạo tài khoản           |
| `/`                             | none  | category + product grid |
| `/products`                     | none  | list                    |
| `/products/:id`                 | none  | PDP                     |
| `/cart`                         | auth  | giỏ                     |
| `/checkout`                     | auth  | COD                     |
| `/account/addresses`            | auth  | sổ địa chỉ              |
| `/orders`, `/orders/:id`        | auth  | lịch sử                 |
| `/admin/dashboard`              | admin | stub ok                 |
| `/admin/categories`             | admin | category + brand tabs   |
| `/admin/products`, `new`, `:id` | admin | catalog                 |
| `/admin/orders`, `:id`          | admin | đơn                     |


## Shell

- `storefront-layout`: header + outlet.
- Header: Login/Register hoặc email + Cart + Orders + Logout; Admin nếu admin.
- `admin-layout` sidebar: Dashboard, Categories, Products, Orders, Logout (`routerLink`).

## Checklist FE

- [ ] Storefront layout + header + `/` public
- [ ] Register
- [ ] Catalog list + PDP (Add to cart disable đến khi có cart API)
- [ ] Cart, addresses, checkout COD, orders + cancel
- [ ] Admin category/product/order
- [ ] Walk loop trên browser + `ng build`

## Later

Guest cart UX, search box, review widget, payment redirect.