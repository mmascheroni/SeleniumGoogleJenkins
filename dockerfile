# Usa la imagen oficial de Jenkins como base
FROM jenkins/jenkins:latest

# Cambia al usuario root para instalar dependencias
USER root

# Instala wget y gnupg2 para descargar paquetes y claves de firma
RUN apt-get update && \
    apt-get install -y wget gnupg2 unzip

# Descarga y añade la clave de firma de Google Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome-archive-keyring.gpg

# Agrega el repositorio de Google Chrome a la lista de fuentes de apt
RUN echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome-archive-keyring.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list

# Actualiza los repositorios de apt y luego instala Google Chrome
RUN apt-get update && \
    apt-get install -y google-chrome-stable

# Descarga y descomprime el ChromeDriver
RUN wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/94.0.4606.61/chromedriver_linux64.zip && \
    unzip /tmp/chromedriver.zip -d /usr/local/bin/ && \
    rm /tmp/chromedriver.zip

# Cambia al usuario jenkins
USER jenkins

# Expone el puerto 8080 para acceder a Jenkins y el puerto 50000 para agentes
EXPOSE 8086
EXPOSE 50000

# Establece el volumen para persistir los datos de Jenkins
VOLUME /var/jenkins_home
