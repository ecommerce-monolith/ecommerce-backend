# Identity

Khách tạo tài khoản, đăng nhập JWT, phân quyền `ROLE_USER` / `ROLE_ADMIN`.

## Đã có

- `POST /api/v1/auth/login` `{ username, password }` → `{ token }`
- `POST /api/v1/auth/register`
- JWT HS512, claim `auth` (space-separated roles)
- FE: login, `authGuard`, `guestGuard`, `roleGuard`, Bearer interceptor

## Rule

- Login lookup theo **email** (field request vẫn tên `username` — không đổi contract).
- Register **phải** ghi `user_authority` = `ROLE_USER`. Hiện tại không gán role → JWT trống.
- `GET /api/v1/auth/me` trả `{ id, email, firstName, lastName, phoneNumber, roles[] }`.
- Warehouse admin path phải `@Secured(ROLE_ADMIN)` (hiện chỉ authenticated).
- Token ~ 1 tuần, `localStorage`. Không refresh, không revoke, không auto-logout 401 (giữ như login spec cũ).

## API

| Method | Path | Auth |
|--------|------|------|
| POST | `/api/v1/auth/login` | public |
| POST | `/api/v1/auth/register` | public |
| GET | `/api/v1/auth/me` | authenticated |

## Checklist BE

- [ ] Register persist `ROLE_USER`
- [ ] `GET /auth/me`
- [ ] Warehouse `@Secured(ADMIN)`
- [ ] Security: permit `/api/v1/auth/**` và `/api/v1/catalog/**`

## Checklist FE (`ecommerce-frontend`)

- [ ] Trang `/register` (Signal Forms) → `POST /auth/register` → redirect `/login`
- [ ] Header: anonymous = Login/Register; logged in = email, Cart, Orders, Logout
- [ ] Link Admin nếu `isAdmin()`
- [ ] Logout gọi `AuthService.logout()` → `/login`

## Later

Refresh token, verify email, quên mật khẩu, MFA, OAuth, auto-logout 401.
