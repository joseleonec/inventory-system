# Spring Boot API con Microsoft Entra ID

Este proyecto requiere configurar variables de entorno para conectarse a la base de datos y proteger la API con Microsoft Entra ID.

---

## 🔧 Variables de entorno requeridas

Debes definir las siguientes variables en tu entorno (ejemplo: `.env`, variables de sistema, o configuración en tu plataforma de despliegue):

```bash
WEB_API_BACKEND_CLIENT_ID=<tu-client-id>
WEB_API_BACKEND_CLIENT_SECRET=<tu-client-secret>
WEB_API_BACKEND_APP_ID_URI=api://<tu-client-id>
AZURE_TENANT_ID=<tu-tenant-id>
```

## Get the token


```bash
curl --request post \
  --url https://login.microsoftonline.com/$AZURE_TENANT_ID/oauth2/v2.0/token \
  --header 'content-type: application/x-www-form-urlencoded' \
  --data client_id=$WEB_API_BACKEND_CLIENT_ID \
  --data client_secret=$WEB_API_BACKEND_CLIENT_SECRET \
  --data scope=$WEB_API_BACKEND_APP_ID_URI/.default \
  --data grant_type=client_credentials

```
