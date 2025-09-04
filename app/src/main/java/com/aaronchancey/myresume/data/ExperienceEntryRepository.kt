package com.aaronchancey.myresume.database

import com.aaronchancey.myresume.database.model.ExperienceEntryEntity
import javax.inject.Inject

class ExperienceEntryRepository @Inject constructor() {
    fun getExperienceEntries() = listOf(
        ExperienceEntryEntity(
            experienceId = 1,
            company = "Insight",
            role = "Principal Software Engineer",
            duration = "June 2017 - May 2025",
            description = """<li><b>Android Developer Lead &ndash; HCA</b></li>
            <ul>
                <li><b>Architected and led the development</b> of a major new feature for the Inspire app, leveraging <b>Jetpack Compose, Kotlin Coroutines, and Flows</b> within a <b>Model-View-Intent (MVI)</b> architecture to create a highly responsive and scalable user interface.</li>
                <li><b>Engineered and launched a suite of complex, user-centric features</b>, including dynamic schedule management, shift swapping, real-time in-app notifications, and comprehensive user profile customization.</li>
                <li><b>Spearheaded significant code modernization and architectural improvements</b>, refactoring legacy XML layouts into declarative <b>Compose views</b>, and introducing a new custom <b>analytics framework</b> and <b>Feature Flag system</b> for data-driven decisions and dynamic production control.</li>
                <li><b>Collaborated extensively with back-end teams</b> to define and integrate new API payloads, ensuring seamless data exchange and robust functionality across the application.</li>
            </ul>
            <li><b>Android Developer &ndash; Pavilion Payments</b></li>
            <ul>
                <li>Spearheaded critical development and maintenance for a high-value financial application, the Patron App, facilitating secure cash transfers between patrons and casinos. Leveraged the <b>Plaid API</b> to enable seamless bank account connectivity and transaction processing, ensuring operational continuity and feature delivery during a key transition period.</li>
                <li>Engineered and implemented a wide range of new features while providing ongoing maintenance and bug fixes. Rapidly assimilated a complex, pre-existing codebase to ensure project continuity and a smooth development lifecycle.</li>
                <li>Enhanced and optimized the Admin Tool, a mission-critical dashboard for managing and monitoring casino touchpoints like slot machines and table games. Contributed to the development of core Bluetooth stack features to enable real-time device monitoring and improve overall operational efficiency.</li>
            </ul>
            <li><b>Android Developer &ndash; Kroger</b></li>
            <ul>
                <li>Architected and developed a proprietary Android library that dynamically renders Jetpack Compose UI from JSON objects, significantly increasing UI flexibility and accelerating development cycles. Implemented comprehensive analytics tracking to monitor user interactions and impressions with on-screen elements.</li>
                <li>Successfully integrated the custom-built UI framework into the flagship Kroger Android application, enabling streamlined feature deployment and providing valuable data-driven insights into user engagement.</li>
            </ul>
            <li><b>Android Developer - Goodyear</b></li>
            <ul>
                <li>Architected and engineered a dynamic front-end dashboard for a NASCAR car tracking application, providing real-time insights into critical race metrics, with a primary focus on pit stop performance and tire degradation.</li>
                <li>Refactored a partially-written Java application into a modern <b>Kotlin</b> architecture, leveraging <b>Coroutines</b> and <b>Flows</b> to efficiently consume and process high-velocity, real-time NASCAR data streams and display car telemetry, lap times, and pit stop information.</li>
            </ul>
            """.trimIndent(),
        ),
        ExperienceEntryEntity(
            experienceId = 2,
            company = "Safelite Auto Glass",
            role = "Senior Mobile Developer",
            duration = "May 2016 - June 2017",
            description = """<ul>
                <li><b>Architected and co-developed a new SaaS</b> platform to manage and assign appointments for a network of field glass repair specialists, directly leading to optimized operational efficiency and streamlined workflow management.</li>
                <li>Collaborated with cross-functional product and data science teams to <b>secure and integrate real-time data feeds</b>, ensuring robust visuals and comprehensive functionality across the platform.</li>
                <li><b>Spearheaded the enhancement and modernization of Android mobile</b> applications used by field technicians, improving overall productivity and significantly elevating the user experience.</li>
            </ul>
            """.trimIndent(),
        ),
        ExperienceEntryEntity(
            experienceId = 3,
            company = "Quick Solutions, Inc.",
            role = "Senior Consultant",
            duration = "October 2012 - September 2015",
            description = """<p>Worked directly with the client to define project success criteria for a public real-estate data mobile application.</p>
            <ul>
                <li>Developed and successfully released a cross-platform mobile application (Android &amp; iOS) using <b>Telerik&rsquo;s AppBuilder</b> (Cordova based).</li>
                <li>Integrated with ESRI&rsquo;s ArcGIS API to display interactive maps and parcel information.</li>
                <li>Built a back-end server to enable flexible, multi-faceted parcel searching.</li>
            </ul>
            """.trimIndent(),
        ),
    )
}
