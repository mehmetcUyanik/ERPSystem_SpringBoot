# ERPSystem_SpringBoot

Bu proje, basit bir ERP (Kurumsal Kaynak Planlaması) sistemi için Spring Boot kullanılarak geliştirilmiştir.

## Proje Hakkında

Bu projenin amacı, bir şirketin temel iş süreçlerini yönetmek için gerekli olan temel ERP fonksiyonlarını içermektedir. Proje, Spring Boot framework'ü kullanılarak geliştirilmiştir ve aşağıdaki bileşenleri içermektedir:

- Kullanıcı oluşturma, silme, güncelleme.
- Ürün oluşturma, silme, güncelleme.
- Sipariş Yönetimi: Müşteri siparişlerinin oluşturulması ve onaylanması veya reddedilmesi.
- Fatura Oluşturma: Onaylanan siparişlere göre fatura oluşturma.

## Kurulum

1. Bu projeyi yerel bilgisayarınıza klonlayın: 
   
    `git clone https://github.com/mehmetcUyanik/ERPSystem_SpringBoot.git `

2.  Proje dizinine gidin:
    
    `cd ERPSystem_SpringBoot` 
    
3.  Uygulamayı başlatmak için aşağıdaki komutu kullanın:
   
    `./mvnw spring-boot:run` 
    
4.  Tarayıcınızda `http://localhost:8080` adresine giderek uygulamayı görüntüleyebilirsiniz.
    

## Kullanım

Uygulamayı başarıyla başlatıldıktan sonra, farklı işlevleri Postman veya Swagger üzerinden istekler atarak deneyimleyebilirsiniz. Kullanıcı oluşturma, ürün oluşturma,
ürünü sipariş sepetine ekleme, sipariş onaylama vs. 
