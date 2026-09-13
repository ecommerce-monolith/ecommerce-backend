# Catalog

Hàng hóa để browse và để admin nhập: category, brand, product, SKU, attribute, ảnh URL.

## Đã có

- Admin `GET/POST /api/v1/admin/products`, attributes
- Warehouse CRUD
- Bảng `products.category_id` / `brand_id` **chưa có entity/FK**
- `sku_images`, `sku_attribute_values` **chưa gắn lúc create**
- Product `status` cột DB **chưa map entity**
- Không có API public browse

## Rule

- Category + Brand là entity; slug unique (kebab, auto từ name nếu client không gửi).
- Public chỉ product/SKU `ACTIVE`. Inactive/deleted → 404 trên catalog public.
- Admin tạo/sửa product: `description`, `categoryId`, `brandId`, `status`, SKUs (price, qty, warehouseId, skuCode?, barcode?, attributeValueIds[], images `{url, sortOrder}`).
- `skuCode` trống thì generate `SKU-` + 8 ký tự; không dùng UUID đầy đủ làm code.
- Ảnh = URL string, không upload file.
- Public detail trả SKU + attributes + images + `availableQuantity` (sau khi inventory xong).

## API

**Public**

| Method | Path | Query |
|--------|------|--------|
| GET | `/api/v1/catalog/categories` | |
| GET | `/api/v1/catalog/products` | `categoryId`, `q`, pageable |
| GET | `/api/v1/catalog/products/{id}` | |

**Admin (`ROLE_ADMIN`)**

| Method | Path |
|--------|------|
| CRUD | `/api/v1/admin/categories` |
| CRUD | `/api/v1/admin/brands` |
| GET/POST | `/api/v1/admin/products` |
| GET/PUT | `/api/v1/admin/products/{id}` |
| GET/POST | `/api/v1/admin/attributes` (existing) |
| CRUD | `/api/v1/admin/warehouses` |

## Checklist BE

- [ ] Liquibase `categories`, `brands`; FK product; map `products.status`
- [ ] Admin category/brand CRUD
- [ ] Product get-by-id + update
- [ ] Create/update persist attribute values + `sku_images`
- [ ] Public catalog permitAll; ẩn hàng không ACTIVE

## Checklist FE

- [ ] Home: chip category + lưới product
- [ ] `/products` list + filter
- [ ] `/products/:id` PDP: chọn attribute → SKU, giá, tồn
- [ ] Admin: category/brand (tab cùng trang)
- [ ] Admin: bảng + form product/SKU/ảnh URL/warehouse

## Later

Elasticsearch, S3 upload, nested category UI sâu, CMS.
