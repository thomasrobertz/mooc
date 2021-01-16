import Email

Email email = new Email()

email.prospectName = "Leaky Pipes"
email.emailAddress = "joe@leakypipes.com"
email.sendDate = new Date() // Groovy can access all Objects in the Java platform
email.notes = "Follow-up Email"

println email.display()

