# Business docs — ecommerce MVP

Thư mục này là **nguồn sự thật cho tính năng**: phạm vi, rule nghiệp vụ, checklist BE/FE.

Không chứa plan kỹ thuật chi tiết (file Java/Angular). Khi implement, bám checklist + rule trong từng feature.

| File | Nội dung |
|------|----------|
| [CHECKLIST.md](./CHECKLIST.md) | Checklist tổng theo phase |
| [features/identity.md](./features/identity.md) | Đăng ký, đăng nhập, role |
| [features/catalog.md](./features/catalog.md) | Category, brand, product, SKU, browse |
| [features/inventory.md](./features/inventory.md) | Tồn kho, reserve / release / commit |
| [features/cart.md](./features/cart.md) | Giỏ hàng |
| [features/checkout-order.md](./features/checkout-order.md) | Địa chỉ, checkout COD, đơn hàng |
| [features/storefront-admin.md](./features/storefront-admin.md) | Trang FE storefront + admin |

## Mục tiêu MVP

Khách **mua được 1 SKU**: browse công khai → login → cart → checkout COD → order. Admin quản lý catalog và trạng thái đơn.

```text
Browse (public) → Login → Cart → Checkout COD
  → PENDING_CONFIRMATION → reserve stock
  → Admin CONFIRMED → SHIPPED → DELIVERED (commit stock)
  → Cancel (PENDING) → release stock
```

## Ngoài MVP (Later)

- Refresh token, verify email, quên mật khẩu, MFA, OAuth
- Guest cart
- Cổng thanh toán (Stripe / VNPay) — MVP chỉ **COD**
- Coupon, thuế, review, wishlist
- Hãng vận chuyển + tracking
- Email / SMS
- Search engine, upload S3 (ảnh = URL)
