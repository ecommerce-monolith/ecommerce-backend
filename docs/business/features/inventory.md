# Inventory

Tồn kho theo SKU + warehouse. Không tách module riêng trong MVP (nằm `module/product`).

## Đã có

- Bảng `inventories` (`quantity`, `reserved_quantity`) ghi lúc tạo product
- `reserved_quantity` **chưa dùng**
- Thiếu FK `warehouse_id` và unique `(sku_id, warehouse_id)`

## Rule

- `available = quantity - reserved_quantity` (cộng mọi warehouse của SKU khi check cart/PDP).
- **Reserve** lúc checkout: `reserved += qty` nếu `available >= qty`. Lock dòng `PESSIMISTIC_WRITE`.
- **Release** lúc cancel (đơn chưa commit): `reserved -= qty`, không âm.
- **Commit** lúc `DELIVERED`: `quantity -= qty`, `reserved -= qty`.
- Reserve lấy từ warehouse còn available nhiều nhất (MVP đơn giản, 1 kho cũng được).
- Không để `quantity < reserved`.
- Hết hàng → `INSUFFICIENT_STOCK`.

## Checklist BE

- [ ] Unique `(sku_id, warehouse_id)` + FK warehouse
- [ ] `InventoryService.available / reserve / release / commit`
- [ ] Test: available 5, reserve 3 ok; reserve thêm 3 fail; release; commit

## Checklist FE

- [ ] PDP hiện `availableQuantity`
- [ ] Cart/checkout hiện lỗi hết hàng từ API

## Later

Điều chỉnh tồn thủ công, transfer kho, backorder.
