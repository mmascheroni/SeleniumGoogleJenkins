# Base image from Jenkins LTS
FROM jenkins/jenkins:lts

USER root

# Install dependencies
RUN apt-get update && apt-get install -y \
    wget \
    gnupg2 \
    apt-transport-https \
    unzip \
    curl \
    software-properties-common \
    && rm -rf /var/lib/apt/lists/*

# Install Google Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable

# Get Chrome version
RUN google-chrome --version > /tmp/chrome_version && \
    CHROME_VERSION=$(cat /tmp/chrome_version | awk '{print $3}') && \
    echo "Using Chrome version: $CHROME_VERSION"

# Install ChromeDriver
RUN CHROME_DRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/${CHROME_DRIVER_VERSION}/chromedriver_linux64.zip && \
    unzip /tmp/chromedriver.zip -d /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver && \
    rm /tmp/chromedriver.zip

# Ensure ChromeDriver is compatible with Chrome
RUN chromedriver --version

# Install necessary plugins (optional, you can add more as needed)
RUN /usr/local/bin/install-plugins.sh workflow-aggregator git github

# Set environment variables for Jenkins
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Set the Jenkins home directory
VOLUME /var/jenkins_home

# Switch back to Jenkin
USER jenkins